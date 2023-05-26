package com.sau.tagem.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.naming.spi.NamingManager;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_measurement")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flower_id")
    private Long flowerId;

    @Column(name = "leaf_volume")
    private Integer leafVolume;

    @Column(name = "leaf_count")
    private Integer leafCount;

    @Column(name = "measurement_date")
    private LocalDateTime measurementDate = LocalDateTime.now();

    public Measurement(Long flowerId, Integer leafCount, Integer leafVolume, LocalDateTime measurementDate) {
        this.flowerId = flowerId;
        this.leafCount = leafCount;
        this.leafVolume = leafVolume;
        this.measurementDate = measurementDate;
    }
}
