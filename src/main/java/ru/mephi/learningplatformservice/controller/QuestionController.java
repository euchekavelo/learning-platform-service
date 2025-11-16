package ru.mephi.learningplatformservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.QuestionRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuestionResponseDto;
import ru.mephi.learningplatformservice.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponseDto> addQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(questionRequestDto));
    }
}
