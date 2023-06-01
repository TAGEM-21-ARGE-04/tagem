package com.sau.tagem.controller;

import com.sau.tagem.dto.Statistic;
import com.sau.tagem.dto.StatisticParams;
import com.sau.tagem.service.StatisticService;
import com.sau.tagem.utils.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    @PostMapping()
    public GenericResponse<Statistic> getAll(@RequestBody StatisticParams statisticParams) {
        return GenericResponse.success(statisticService.getStatistics(statisticParams));
    }
}
