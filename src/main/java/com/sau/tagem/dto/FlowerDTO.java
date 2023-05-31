package com.sau.tagem.dto;

import com.sau.tagem.model.Flower;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO implements Serializable {
    private Long id;
    private Integer groupIndex;

    public FlowerDTO(Flower flower) {
        this.id = flower.getId();
        this.groupIndex = flower.getGroupIndex();
    }
}
