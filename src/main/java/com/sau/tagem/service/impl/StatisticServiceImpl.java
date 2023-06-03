package com.sau.tagem.service.impl;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.dto.StatisticParams;
import com.sau.tagem.model.Flower;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.StatisticRepository;
import com.sau.tagem.service.MeasurementService;
import com.sau.tagem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final MeasurementService measurementService;
    private final StatisticRepository statisticRepository;

    @Override
    public Statistic getStatistics(StatisticParams statisticParams) {
        LocalDateTime startDate = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDate = LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()).withHour(23).withMinute(59).withSecond(59).withNano(999999999);

        log.info("startDate: {}, endDate: {}", startDate, endDate);

        if (statisticParams.getGroups() != null && statisticParams.getGroups().size() != 0) {
            return statisticRepository.generateGroupStatistics(startDate, endDate, statisticParams);
        }

        return statisticRepository.generateFlowerStatistics(startDate, endDate, statisticParams);
    }
}
