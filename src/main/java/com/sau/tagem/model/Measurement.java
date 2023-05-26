package com.sau.tagem.model;

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
}
