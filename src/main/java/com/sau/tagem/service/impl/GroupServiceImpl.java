package com.sau.tagem.service.impl;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.GroupRepository;
import com.sau.tagem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Override
    public GroupDTO save(GroupDTO groupDTO) {
        Group group = groupRepository.save(
                modelMapper.map(groupDTO, Group.class)
        );

        return modelMapper.map(group, GroupDTO.class);
    }
}
