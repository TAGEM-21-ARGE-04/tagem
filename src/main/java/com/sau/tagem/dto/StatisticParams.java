package com.sau.tagem.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticParams {
    private GroupDTO group;
    private List<GroupDTO> groups;
    private String statisticDate;
    private String queryType;
    private String queryVariable;
}
