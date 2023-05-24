package com.sau.tagem.controller;

import com.sau.tagem.dto.GroupDTO;
import com.sau.tagem.model.Group;
import com.sau.tagem.service.GroupService;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/group")
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public GenericResponse<List<GroupDTO>> getAll() {
        return GenericResponse.success(
                groupService.getAll()
        );
    }

    @PostMapping
    public GenericResponse<GroupDTO> save(@RequestBody GroupDTO groupDTO) {
        return GenericResponse.success(groupService.save(groupDTO));
    }
}
