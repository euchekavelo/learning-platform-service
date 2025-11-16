package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.QuizSubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuizSubmissionResponseDto;
import ru.mephi.learningplatformservice.service.QuizSubmissionService;

@RestController
@RequestMapping("/api/quiz-submissions")
@RequiredArgsConstructor
public class QuizSubmissionController {

    private final QuizSubmissionService quizSubmissionService;

    @PostMapping
    public ResponseEntity<QuizSubmissionResponseDto> submitQuiz(@Valid @RequestBody QuizSubmissionRequestDto quizSubmissionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quizSubmissionService.takeQuiz(quizSubmissionRequestDto));
    }
}
