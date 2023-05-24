package com.sau.tagem.service.impl;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.GroupRepository;
import com.sau.tagem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<GroupDTO> getAll() {
        return groupRepository.getAll();
    }

    @Override
    public GroupDTO getById(Long id) {
        Optional<GroupDTO> optionalGroupDTO = groupRepository.getDetailsById(id);

        if (optionalGroupDTO.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return optionalGroupDTO.get();
    }
}
