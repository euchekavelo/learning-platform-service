package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.learningplatformservice.dto.request.CourseRequestDto;
import ru.mephi.learningplatformservice.dto.response.CourseResponseDto;
import ru.mephi.learningplatformservice.dto.response.UserResponseDto;
import ru.mephi.learningplatformservice.service.CourseService;
import ru.mephi.learningplatformservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final UserService userService;

    @PostMapping
    private ResponseEntity<CourseResponseDto> addCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseRequestDto));
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable Integer courseId) {
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PutMapping("/{courseId}")
    private ResponseEntity<CourseResponseDto> updateCourse(@PathVariable Integer courseId,
                                                           @Valid @RequestBody CourseRequestDto courseRequestDto) {

        return ResponseEntity.ok(courseService.updateCourseById(courseId, courseRequestDto));
    }

    @DeleteMapping("/{courseId}")
    private ResponseEntity<Void> deleteCourseById(@PathVariable Integer courseId) {
        courseService.deleteCourseById(courseId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{courseId}/users")
    public ResponseEntity<List<UserResponseDto>> getCourseStudents(@PathVariable Integer courseId) {
        return ResponseEntity.ok(userService.getStudentsByCourseId(courseId));
    }
}
