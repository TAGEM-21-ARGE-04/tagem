package com.sau.tagem.service.impl;

import com.sau.tagem.dto.MeasurementGroupDTO;
import com.sau.tagem.model.Measurement;
import com.sau.tagem.repository.MeasurementRepository;
import com.sau.tagem.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeasurementServiceImpl implements MeasurementService {

    private final MeasurementRepository measurementRepository;

    @Override
    @Transactional
    public Boolean save(MeasurementGroupDTO measurementGroupDTO) {
        try {
            for (Measurement measurement : measurementGroupDTO.getMeasurements()) {
                measurementRepository.save(measurement);
            }
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("measurementSaveFailed");
        }
    }

    @Override
    public List<Measurement> getAllByFlowerId(Long id) {
        return measurementRepository.getAllByFlowerId(id);
    }

    @Override
    public List<Object> getLeafCountDiffForOneYear(LocalDateTime startDate, LocalDateTime endDate) {
        //return measurementRepository.getLeafCountDifForOneYear(startDate, endDate);
        return null;
    }
}
