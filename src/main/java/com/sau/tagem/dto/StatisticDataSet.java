package com.sau.tagem.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class StatisticDataSet {
    private String name;

    private Object[] data;

    public StatisticDataSet(String name, int dataSize) {
        this.name = name;
        Object array[] = new Object[dataSize];

        Arrays.fill(array, 0);
        data = array;
    }
}
