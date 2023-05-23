package com.sau.tagem.controller;

import com.sau.tagem.model.Flower;
import com.sau.tagem.service.FlowerService;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/flower")
@RequiredArgsConstructor
public class FlowerController {

    private final FlowerService flowerService;

    @PostMapping
    public GenericResponse<Flower> save(@RequestBody Flower flower) {
        return GenericResponse.success(flowerService.save(flower));
    }
}
