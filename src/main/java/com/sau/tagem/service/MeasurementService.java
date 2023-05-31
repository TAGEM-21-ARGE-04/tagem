package com.sau.tagem.service;

import com.sau.tagem.dto.MeasurementGroupDTO;
import com.sau.tagem.model.Measurement;

import java.time.LocalDateTime;
import java.util.List;

public interface MeasurementService {
    Boolean save(MeasurementGroupDTO measurement);

    List<Measurement> getAllByFlowerId(Long id);

    List<Object> getLeafCountDiffForOneYear(LocalDateTime startDate, LocalDateTime endDate);
}
