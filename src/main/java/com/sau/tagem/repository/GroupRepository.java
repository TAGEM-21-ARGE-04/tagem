package com.sau.tagem.repository;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(
            "SELECT NEW com.sau.tagem.dto.GroupDTO(g.id, g.name, g.flowerCount, g.description) " +
            "FROM Group g " +
            "ORDER BY g.name  "
    )
    List<GroupDTO> getAll();

    @Query(
            "SELECT NEW com.sau.tagem.dto.GroupDTO(g) " +
            "FROM Group g " +
            "WHERE g.id = :id"
    )
    Optional<GroupDTO> getDetailsById(Long id);

    @Query(
            "SELECT name FROM Group WHERE id = :id"
    )
    String getGroupNameById(@Param("id") Long id);
}