package com.sau.tagem.service.impl;

import com.sau.tagem.dto.request.QRCreate;
import com.sau.tagem.model.Group;
import com.sau.tagem.service.FlowerService;
import com.sau.tagem.service.QRService;
import com.sau.tagem.utils.QRCodeGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class QRServiceImpl implements QRService {

    private final Integer qrCodeSize = 500;
    private final FlowerService flowerService;

    @Override
    public ResponseEntity<byte[]> createQR(QRCreate qr) {
        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < qr.getGroupIds().size(); i++) {
            Long id = qr.getGroupIds().get(0);
            groups.add(
                    new Group(
                            id,
                            flowerService.getAllByGroupId(id)
                    )
            );
        }

        // TODO THIS ALGORITHM SHOULD BE IMPROVED
        int count = groups.stream().mapToInt(group -> group.getFlowers().size()).sum();
        int qrCodeVolume = count * qrCodeSize * qrCodeSize;
        int paperVolume = qr.getPaperSize().getWidth() * qr.getPaperSize().getHeight();

        log.info("QR Count: {}, QR VOLUME {}, PAPER VOLUME {}", count, qrCodeVolume, paperVolume);
        if (count * qrCodeSize * qrCodeSize > qr.getPaperSize().getWidth() * qr.getPaperSize().getHeight() ) {
            throw new IllegalArgumentException("TOO many qr code");
        }

        try {
            List<List<byte[]>> groupImages = QRCodeGenerator.getQRCodeImages(groups);

            BufferedImage combinedImage = new BufferedImage(qr.getPaperSize().getWidth(), qr.getPaperSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = combinedImage.createGraphics();

            int i = 0;
            int xPosition = 0;
            int yPosition = 0;
            int dividerPoint = qr.getPaperSize().getWidth() / qrCodeSize;

            log.info("DIVIDERPOINT {}", dividerPoint);
            for (List<byte[]> imageList: groupImages) {
                for (byte[] imageData : imageList) {
                    try {
                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));

                        log.info("x: {}, y: {}", xPosition, yPosition);
                        if (i == dividerPoint) {
                            yPosition = yPosition + qrCodeSize;
                            xPosition = 0;
                            i = 0;
                        }

                        graphics.drawImage(image, xPosition, yPosition, null);

                        xPosition += image.getWidth();
                        i++;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                yPosition = yPosition + qrCodeSize;
                xPosition = 0;
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ImageIO.write(combinedImage, "jpg", baos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] combinedImageData = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(combinedImageData.length);
            headers.setContentType(MediaType.IMAGE_JPEG);

            // ResponseEntity oluşturma ve dönme
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(combinedImageData);
        } catch (Exception e) {
            log.info(e, e);
            throw new IllegalArgumentException("createQRException");
        }
    }
}
