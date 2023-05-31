package com.sau.tagem.dto;

import com.sau.tagem.model.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO implements Serializable {
    private Long id;

    private String name;

    private String description;

    private Integer flowerCount;

    private LocalDateTime startDate = LocalDateTime.now();

    private LocalDateTime lastProcessDate = LocalDateTime.now();

    private List<FlowerDTO> flowers;

    public GroupDTO(Long id, String name, Integer flowerCount, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.flowerCount = flowerCount;
    }

    public GroupDTO(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.flowers = group.getFlowers().stream().map(FlowerDTO::new).toList();
    }
}
