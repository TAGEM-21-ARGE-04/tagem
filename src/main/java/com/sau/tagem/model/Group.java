package com.sau.tagem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "t_group")
public class Group extends Auditable<Long> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "flower_count")
    private Integer flowerCount;

    @OneToMany(mappedBy = "group")
    List<Flower> flowers;

    public Group(Long id, List<Flower> flowers) {
        this.id = id;
        this.flowers = flowers;
    }
}
