package com.sau.tagem.dto.request;

import com.sau.tagem.enums.PaperSize;
import lombok.Data;

import java.util.List;

@Data
public class QRCreate {
    private List<Long> groupIds;
    private PaperSize paperSize;
}
