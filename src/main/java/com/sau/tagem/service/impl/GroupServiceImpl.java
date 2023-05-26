package com.sau.tagem.service.impl;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Flower;
import com.sau.tagem.model.Group;
import com.sau.tagem.repository.GroupRepository;
import com.sau.tagem.service.FlowerService;
import com.sau.tagem.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final ModelMapper modelMapper;
    private final FlowerService flowerService;
    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public GroupDTO save(GroupDTO groupDTO) {
        Group group = groupRepository.save(
                modelMapper.map(groupDTO, Group.class)
        );

        group.setFlowers(new ArrayList<>());

        for (int i = 1; i < groupDTO.getFlowerCount() + 1; i++) {
            Flower flower = flowerService.save(new Flower(i, group));
            group.getFlowers().add(flower);
        }

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
