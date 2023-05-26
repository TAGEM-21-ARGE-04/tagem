package com.sau.tagem.service;

import com.sau.tagem.dto.request.QRCreate;
import org.springframework.http.ResponseEntity;

public interface QRService {

    ResponseEntity<byte[]> createQR(QRCreate qr);
}
