package com.sau.tagem.repository;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.dto.StatisticDataSet;
import com.sau.tagem.model.Flower;
import com.sau.tagem.model.Group;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Repository
public class StatisticRepository {

    private static Map<String, List<String>> labels;
    static {
        labels = new HashMap<>();
        labels.put("month", List.of("January", "February", "March", "April", "May", "June", "July", "AUGUST", "SEPTEMBER", "NOVEMBER", "DECEMBER"));
    }

    @PersistenceContext
    private EntityManager entityManager;

    public Statistic getLeafCountDiffForOneYear(LocalDateTime startDate, LocalDateTime endDate, Group group) {
        Statistic statistic = new Statistic();
        statistic.setLabels(labels.get("month"));

        for (Flower flower : group.getFlowers()) {
            statistic.getDataSets().add(new StatisticDataSet(flower.getId().toString(), 12));
        }

            String jpql = "SELECT flowerId AS flowerId, FUNCTION('MONTH', m.measurementDate) AS month, AVG(m.leafCount) AS totalAmount "
                    + "FROM Measurement m "
                    + "WHERE m.measurementDate >= :startDate AND m.measurementDate <= :endDate AND m.flowerId in :flowerIds "
                    + "GROUP BY month, flowerId";

            List<Object[]> rows = entityManager.createQuery(jpql)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate)
                    .setParameter("flowerIds", group.getFlowers().stream().map(flower -> flower.getId()).collect(Collectors.toList()))
                    .getResultList();

            for (Object[] row : rows) {
                //statistic.getLabels().add(String.valueOf(row[0]));
                Long id = (Long) row[0];
                log.info("id: {}, month : {}, data: {}", id, row[1], row[2]);

                statistic.getDataSets().stream().filter(d -> d.getName().equals(id.toString())).findFirst().get().getData()[(int) row[1] - 1] = row[2];
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {

            }
        return statistic;
    }
}