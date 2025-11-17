package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.learningplatformservice.dto.request.EvaluateRequestDto;
import ru.mephi.learningplatformservice.dto.request.SubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.SubmissionResponseDto;
import ru.mephi.learningplatformservice.service.SubmissionService;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponseDto> sendSubmission(@Valid @RequestBody SubmissionRequestDto submissionRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(submissionService.sendSubmission(submissionRequestDto));
    }

    @PatchMapping("/{submissionId}")
    public ResponseEntity<SubmissionResponseDto> evaluateSubmission(@PathVariable Integer submissionId,
                                                                    @Valid @RequestBody EvaluateRequestDto evaluateRequestDto) {

        return ResponseEntity.ok(submissionService.evaluateSubmission(submissionId, evaluateRequestDto));
    }
}
