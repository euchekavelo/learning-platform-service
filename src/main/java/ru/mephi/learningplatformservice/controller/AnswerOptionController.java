package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.AnswerRequestDto;
import ru.mephi.learningplatformservice.dto.response.AnswerResponseDto;
import ru.mephi.learningplatformservice.service.AnswerOptionService;

@RestController
@RequestMapping("/api/answer-options")
@RequiredArgsConstructor
public class AnswerOptionController {

    private final AnswerOptionService answerOptionService;

    @PostMapping
    public ResponseEntity<AnswerResponseDto> addAnswerOption(@Valid @RequestBody AnswerRequestDto answerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(answerOptionService.addAnswerOption(answerRequestDto));
    }
}
