package com.sau.tagem.service.impl;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.dto.StatisticParams;
import com.sau.tagem.model.Flower;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.StatisticRepository;
import com.sau.tagem.service.MeasurementService;
import com.sau.tagem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    private final MeasurementService measurementService;
    private final StatisticRepository statisticRepository;

    @Override
    public Statistic getStatistics(StatisticParams statisticParams) {
        LocalDateTime startDate = LocalDateTime.now().minusYears(1);
        LocalDateTime endDate = LocalDateTime.now();

        //List<Object> objects = measurementService.getLeafCountDiffForOneYear(startDate, endDate);

        Group group = new Group();
        group.setFlowers(List.of(new Flower(2L), new Flower(3L), new Flower(4L), new Flower(5L), new Flower(6L)));
        return statisticRepository.getLeafCountDiffForOneYear(startDate, endDate, group, statisticParams);
    }
}
