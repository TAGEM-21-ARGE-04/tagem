package com.sau.tagem.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Statistic {
    private List<String> labels = new ArrayList<>();
    private List<StatisticDataSet> dataSets = new ArrayList<>();
}
