package com.sau.tagem.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlowerDTO {
    private Long id;
    private String name;

    private GroupDTO group;
}
