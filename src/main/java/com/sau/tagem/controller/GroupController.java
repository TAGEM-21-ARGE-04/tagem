package com.sau.tagem.controller;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import com.sau.tagem.service.GroupService;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/group")
public class GroupController {
    private final GroupService groupService;

    @PostMapping
    public GenericResponse<GroupDTO> save(@RequestBody GroupDTO groupDTO) {
        return GenericResponse.success(groupService.save(groupDTO));
    }
}
