package com.sau.tagem.controller;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(statisticService.getStatistics());
    }
}
