package com.sau.tagem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GroupDTO {
    private Long id;

    private String name;

    private String description;

    private List<FlowerDTO> flowers;
}
