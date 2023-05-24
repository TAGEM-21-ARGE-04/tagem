package com.sau.tagem.controller;

import com.sau.tagem.utils.QRCodeGenerator;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("api/qr")
public class QRController {

    @GetMapping(value = "/{flowerId}")
    public ResponseEntity<InputStreamResource> generateQRCode(@PathVariable("flowerId") Long flowerId) throws Exception {
        ByteArrayResource byteArrayResource = new ByteArrayResource(
                QRCodeGenerator.getQRCodeImage(flowerId.toString())
        );

        InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(byteArrayResource.getByteArray()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(byteArrayResource.contentLength());
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    @GetMapping(value = "/{flowerId}/{width}/{height}")
    public ResponseEntity<byte[]> generateQRCode(
            @PathVariable("flowerId") Long flowerId,
            @PathVariable("width") Integer width,
            @PathVariable("height") Integer height
    ) throws Exception {

        return ResponseEntity.status(HttpStatus.OK).body(
                QRCodeGenerator.getQRCodeImage(flowerId.toString(), width, height)
        );
    }

    @GetMapping("/combineImages")
    public ResponseEntity<byte[]> combineImages() {
        try {
            List<byte[]> imageList = Arrays.asList(
                    QRCodeGenerator.getQRCodeImage("1"),
                    QRCodeGenerator.getQRCodeImage("2"),
                    QRCodeGenerator.getQRCodeImage("3"),
                    QRCodeGenerator.getQRCodeImage("4"),
                    QRCodeGenerator.getQRCodeImage("5"),
                    QRCodeGenerator.getQRCodeImage("6")
            );

            // Genel boyutu belirleyin
            int width = 600;
            int height = 400;

            // Birleştirilmiş resim için BufferedImage oluşturma
            BufferedImage combinedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = combinedImage.createGraphics();

            // Her bir resmi birleştirilmiş resim üzerine çizin
            int xPosition = 0;
            int yPosition = 0;
            int i = 0;
            for (byte[] imageData : imageList) {
                try {
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageData));

                    log.info("x: {}, y: {}", xPosition, yPosition);
                    if (i == 3) {
                        yPosition = 200;
                        xPosition = 0;
                    }

                    graphics.drawImage(image, xPosition, yPosition, null);

                    // Bir sonraki resim için x konumunu güncelleme
                    xPosition += image.getWidth();
                    i++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // BufferedImage'ı byte dizisine dönüştürme
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ImageIO.write(combinedImage, "jpg", baos);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] combinedImageData = baos.toByteArray();

            // Response Headers ayarlama
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(combinedImageData.length);
            headers.setContentType(MediaType.IMAGE_JPEG);

            // ResponseEntity oluşturma ve dönme
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(combinedImageData);
        } catch (Exception e) {
            log.info(e, e);
        }

        return null;
    }
}
