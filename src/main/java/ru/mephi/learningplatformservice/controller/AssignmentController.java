package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.learningplatformservice.dto.request.AssignmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.AssignmentResponseDto;
import ru.mephi.learningplatformservice.dto.response.SubmissionResponseDto;
import ru.mephi.learningplatformservice.service.AssignmentService;
import ru.mephi.learningplatformservice.service.SubmissionService;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<AssignmentResponseDto> addAssignment(@Valid @RequestBody AssignmentRequestDto assignmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.addAssignment(assignmentRequestDto));
    }

    @GetMapping("/{assignmentId}/submissions")
    public ResponseEntity<List<SubmissionResponseDto>> getListOfSubmissionToAssignment(@PathVariable Integer assignmentId,
                                                        @RequestParam Integer teacherId) {

        return ResponseEntity.ok(submissionService.getListOfSubmissionToAssignment(assignmentId, teacherId));
    }
}
