package com.sau.tagem.service;

import com.sau.tagem.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    GroupDTO save(GroupDTO groupDTO);

    List<GroupDTO> getAll();
}
