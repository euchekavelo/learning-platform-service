package ru.mephi.learningplatformservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.learningplatformservice.dto.request.EnrollmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.EnrollmentResponseDto;
import ru.mephi.learningplatformservice.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDto> createEnroll(@RequestBody EnrollmentRequestDto enrollmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.createEnroll(enrollmentRequestDto));
    }

    @DeleteMapping("/{enrollId}")
    public ResponseEntity<Void> deleteEnrollById(@PathVariable Integer enrollId) {
        enrollmentService.deleteEnrollById(enrollId);

        return ResponseEntity.noContent().build();
    }
}
