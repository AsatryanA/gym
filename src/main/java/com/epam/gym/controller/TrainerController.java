package com.epam.gym.controller;

import com.epam.gym.entity.dto.request.ToggleActiveDTO;
import com.epam.gym.entity.dto.request.TrainerRequestDTO;
import com.epam.gym.entity.dto.request.TrainerUpdateDTO;
import com.epam.gym.entity.dto.response.TrainerCreateResponseDTO;
import com.epam.gym.entity.dto.response.TrainerResponseDTO;
import com.epam.gym.entity.dto.response.TrainerTrainingDTO;
import com.epam.gym.service.TrainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @PostMapping
    ResponseEntity<TrainerCreateResponseDTO> create(@Valid @RequestBody TrainerRequestDTO trainerRequestDTO) {
        return new ResponseEntity<>(trainerService.create(trainerRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<TrainerResponseDTO> getById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(trainerService.getById(id), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<TrainerResponseDTO> update(@Valid @RequestBody TrainerUpdateDTO trainerUpdateDTO) {
        return new ResponseEntity<>(trainerService.update(trainerUpdateDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}/trainings")
    ResponseEntity<List<TrainerTrainingDTO>> getTrainings(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(trainerService.getTrainings(id), HttpStatus.OK);
    }

    @PatchMapping("/toggle-active")
    ResponseEntity<Void> toggleActive(@Valid @RequestBody ToggleActiveDTO toggleActiveDTO) {
        trainerService.toggleActive(toggleActiveDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
