package com.sau.tagem.service;

import com.sau.tagem.dto.MeasurementGroupDTO;
import com.sau.tagem.model.Measurement;

public interface MeasurementService {
    Boolean save(MeasurementGroupDTO measurement);
}
