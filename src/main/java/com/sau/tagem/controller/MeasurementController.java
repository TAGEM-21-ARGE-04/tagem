package com.sau.tagem.controller;

import com.sau.tagem.dto.MeasurementGroupDTO;
import com.sau.tagem.model.Measurement;
import com.sau.tagem.service.MeasurementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    @GetMapping("/{id}")
    public List<Measurement> getAllByFlowerId(@PathVariable("id") Long id) {
        return measurementService.getAllByFlowerId(id);
    }

    @PostMapping
    public Boolean save(@RequestBody MeasurementGroupDTO measurementGroupDTO) {
       return measurementService.save(measurementGroupDTO);
    }
}
