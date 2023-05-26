package com.sau.tagem.service;

import com.sau.tagem.dto.request.QRCreate;
import org.springframework.stereotype.Service;

@Service
public interface QRService {

    byte[] createQR(QRCreate qr);
}
