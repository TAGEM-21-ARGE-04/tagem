package com.sau.tagem.repository;

import com.sau.tagem.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    @Query(
            "SELECT new com.sau.tagem.model.Flower(f.id) FROM Flower f " +
            "WHERE f.group.id = :groupId "
    )
    List<Flower> getAllByGroupId(@Param("groupId") Long groupId);
}
