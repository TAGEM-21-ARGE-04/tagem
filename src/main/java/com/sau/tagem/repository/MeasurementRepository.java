package com.sau.tagem.repository;

import com.sau.tagem.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query(
            "SELECT NEW com.sau.tagem.model.Measurement(m.flowerId, m.leafCount, m.leafVolume, m.measurementDate) FROM Measurement m " +
            "WHERE m.flowerId = :flowerId " +
            "ORDER BY m.measurementDate ASC "
    )
    List<Measurement> getAllByFlowerId(@Param("flowerId") Long flowerId);
}
