package ru.mephi.learningplatformservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mephi.learningplatformservice.dto.request.UserRequestDto;
import ru.mephi.learningplatformservice.dto.response.CourseResponseDto;
import ru.mephi.learningplatformservice.dto.response.UserResponseDto;
import ru.mephi.learningplatformservice.service.CourseService;
import ru.mephi.learningplatformservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }

    @GetMapping("/{userId}/courses")
    public ResponseEntity<List<CourseResponseDto>> getUserCourses(@PathVariable Integer userId) {
        return ResponseEntity.ok(courseService.getUserCourses(userId));
    }
}
