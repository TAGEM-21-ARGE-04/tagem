package com.sau.tagem.controller;

import com.sau.tagem.utils.QRCodeGenerator;
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

import java.io.ByteArrayInputStream;

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

}
