package com.sau.tagem.controller;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.service.StatisticService;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping()
    public GenericResponse<Statistic> getAll() {
        return GenericResponse.success(statisticService.getStatistics());
    }
}
