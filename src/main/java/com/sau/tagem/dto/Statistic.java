package com.sau.tagem.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Statistic {
    private List<String> labels = new ArrayList<>();
    private List<StatisticDataSet> dataSets = new ArrayList<>();

    public Statistic(List<String> labels) {
        this.labels = labels;
    }
}
