package com.sau.tagem.dto;

import com.sau.tagem.model.Group;
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

    private Integer flowerCount;

    private List<FlowerDTO> flowers;

    public GroupDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public GroupDTO(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.flowers = group.getFlowers().stream().map(FlowerDTO::new).toList();
    }
}
