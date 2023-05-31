package com.sau.tagem.service.impl;

import com.sau.tagem.dto.request.QRCreate;
import com.sau.tagem.enums.PaperSize;
import com.sau.tagem.model.Flower;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.GroupRepository;
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
    private final GroupRepository groupRepository;

    private Integer calculateGroupQRRowC(Group group, PaperSize paperSize) {
        Integer maxRowQRSize = paperSize.getWidth() / qrCodeSize;

        Integer rowSize = group.getFlowers().size() / maxRowQRSize;

        if (group.getFlowers().size() % maxRowQRSize > 0) {
            rowSize++;
        }

        return rowSize;
    }

    @Override
    public ResponseEntity<byte[]> createQR(QRCreate qr) {
        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < qr.getGroupIds().size(); i++) {
            Long id = qr.getGroupIds().get(0);
            groups.add(
                    new Group(
                            id,
                            groupRepository.getGroupNameById(id),
                            flowerService.getAllByGroupId(id)
                    )
            );
        }

        // TODO THIS ALGORITHM SHOULD BE IMPROVED
        int count = groups.stream().mapToInt(group -> group.getFlowers().size()).sum();
        int qrCodeVolume = count * qrCodeSize * qrCodeSize;
        int paperVolume = qr.getPaperSize().getWidth() * qr.getPaperSize().getHeight();

        Integer rowSize = groups.stream().mapToInt(group -> calculateGroupQRRowC(group, qr.getPaperSize())).sum();
        log.info("rowSize");

        log.info("QR Count: {}, QR VOLUME {}, PAPER VOLUME {}", count, qrCodeVolume, paperVolume);
        if (count * qrCodeSize * qrCodeSize > qr.getPaperSize().getWidth() * qr.getPaperSize().getHeight() ) {
            throw new IllegalArgumentException("TOO many qr code");
        }

        try {
            List<List<byte[]>> groupImages = QRCodeGenerator.getQRCodeImages(groups);

            BufferedImage combinedImage = new BufferedImage(qr.getPaperSize().getWidth(), qr.getPaperSize().getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = combinedImage.createGraphics();

            int x = 0;
            int y = 0;
            int dividePoint = (qr.getPaperSize().getWidth() / qrCodeSize);

            Font font = new Font("Arial", Font.PLAIN, 12);
            Color textColor = Color.RED;
            graphics.setFont(font);
            graphics.setColor(textColor);

            for (Group group : groups) {
                List<byte[]> images = groupImages.get(0);

                String text = group.getName();

                y+=20;
                x+=20;
                graphics.drawString(text, x, y);
                x=0;
                y+=20;

                for (int i = 0; i < group.getFlowers().size(); i++) {
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(images.get(i)));

                    if (i != 0 && i % dividePoint == 0) {
                        y += qrCodeSize;
                        x = 0;
                    }

                    log.info("x: {} y:{} i:{}", x, y, i);

                    graphics.drawImage(image, x, y, null);

                    x+=qrCodeSize;
                }
                x=0;
                y+=qrCodeSize;
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
