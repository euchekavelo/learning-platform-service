package ru.mephi.learningplatformservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.LessonRequestDto;
import ru.mephi.learningplatformservice.dto.response.LessonResponseDto;
import ru.mephi.learningplatformservice.service.LessonService;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponseDto> addLessons(@RequestBody LessonRequestDto lessonRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.addLessons(lessonRequestDto));
    }
}
