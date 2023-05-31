package com.sau.tagem.repository;

import com.sau.tagem.dto.Statistic;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class StatisticRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Statistic getLeafCountDiffForOneYear(LocalDateTime startDate, LocalDateTime endDate) {
        Statistic statistic = new Statistic();

        String jpql = "SELECT FUNCTION('MONTH', m.measurementDate) AS month, SUM(m.leafCount) AS totalAmount "
                + "FROM Measurement m "
                + "WHERE m.measurementDate >= :startDate AND m.measurementDate <= :endDate "
                + "GROUP BY month";

        List<Object[]> rows = entityManager.createQuery(jpql)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();


        for (Object[] row : rows) {
            statistic.getLabels().add(String.valueOf(row[0]));
            statistic.getData().add(row[1]);
        }

        return statistic;
    }
}
