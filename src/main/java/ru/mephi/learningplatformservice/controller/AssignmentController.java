package ru.mephi.learningplatformservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mephi.learningplatformservice.dto.request.AssignmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.AssignmentResponseDto;
import ru.mephi.learningplatformservice.service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> addAssignment(@RequestBody AssignmentRequestDto assignmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.addAssignment(assignmentRequestDto));
    }
}
