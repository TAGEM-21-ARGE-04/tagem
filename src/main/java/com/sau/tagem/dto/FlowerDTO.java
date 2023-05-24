package com.sau.tagem.dto;

import com.sau.tagem.model.Flower;
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

    public FlowerDTO(Flower flower) {
        this.id = flower.getId();
    }
}
