package com.traini8.traini8.controllers;

import com.traini8.traini8.dtos.TrainingCenterDto;
import com.traini8.traini8.models.TrainingCenter;
import com.traini8.traini8.services.TrainingCenterService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainingcenter")
public class TrainingCenterController {

    TrainingCenterService trainingCenterService;

    TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }


    @PostMapping()
    public TrainingCenterDto createTrainingCenter(@Valid @RequestBody TrainingCenterDto trainingCenterDto) {
        return trainingCenterService.createTrainingCenter(trainingCenterDto);
    }

    @GetMapping()
    public List<TrainingCenterDto> getAllTrainingCenters() {
        return trainingCenterService.findAllTrainingCenters();
    }
    @GetMapping("/{id}")
    public TrainingCenterDto getTrainingCenterByCenterCode(@PathVariable("id") Long centerCode) {
        return trainingCenterService.findTrainingCenterByCenterCode(centerCode);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingCenterByCenterCode(@PathVariable("id") Long centerCode) {
        trainingCenterService.deleteTrainingCenter(centerCode);
    }

    @PatchMapping("/{id}")
    public TrainingCenterDto updateTrainingCenter(@PathVariable("id") Long centerCode, @RequestBody TrainingCenterDto trainingCenterDto) {
        return trainingCenterService.updateTrainingCenter(centerCode, trainingCenterDto);
    }
}
