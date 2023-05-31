package com.sau.tagem.service.impl;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.repository.StatisticRepository;
import com.sau.tagem.service.MeasurementService;
import com.sau.tagem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final MeasurementService measurementService;
    private final StatisticRepository statisticRepository;

    @Override
    public Statistic getStatistics() {
        LocalDateTime startDate = LocalDateTime.now().minusYears(1);
        LocalDateTime endDate = LocalDateTime.now();

        //List<Object> objects = measurementService.getLeafCountDiffForOneYear(startDate, endDate);

        return statisticRepository.getLeafCountDiffForOneYear(startDate, endDate);
    }
}
