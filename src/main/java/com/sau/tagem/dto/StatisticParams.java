package com.sau.tagem.dto;

import lombok.Data;

@Data
public class StatisticParams {
    private GroupDTO group;
    private String statisticDate;
    private String queryType;
    private String queryVariable;
}
