package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.ModuleRequestDto;
import ru.mephi.learningplatformservice.dto.response.ModuleResponseDto;
import ru.mephi.learningplatformservice.service.ModuleService;

@RestController
@RequestMapping("/api/modules")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;

    @PostMapping
    public ResponseEntity<ModuleResponseDto> addModules(@Valid @RequestBody ModuleRequestDto moduleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.addModules(moduleRequestDto));
    }
}
