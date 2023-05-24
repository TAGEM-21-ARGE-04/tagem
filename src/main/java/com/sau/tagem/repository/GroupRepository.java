package com.sau.tagem.repository;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query(
            "SELECT NEW com.sau.tagem.dto.GroupDTO(g.id, g.name, g.description) " +
            "FROM Group g " +
            "ORDER BY g.name  "
    )
    List<GroupDTO> getAll();
}