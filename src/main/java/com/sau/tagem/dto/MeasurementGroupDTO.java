package com.sau.tagem.dto;

import com.sau.tagem.model.Measurement;
import lombok.Data;

import java.util.List;

@Data
public class MeasurementGroupDTO {
    private List<Measurement> measurements;
}
