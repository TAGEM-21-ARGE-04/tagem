package com.sau.tagem.service;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.dto.StatisticParams;

public interface StatisticService {
    Statistic getStatistics(StatisticParams statisticParams);
}
