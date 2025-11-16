package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.QuizRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuizResponseDto;
import ru.mephi.learningplatformservice.service.QuizService;

@RestController
@RequestMapping("/api/quizes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponseDto> addQuiz(@Valid @RequestBody QuizRequestDto quizRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.addQuiz(quizRequestDto));
    }
}
