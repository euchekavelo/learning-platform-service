package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.EnrollmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.EnrollmentResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.model.Course;
import ru.mephi.learningplatformservice.model.Enrollment;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.EnrollStatus;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.repository.EnrollmentRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseService courseService;
    private final UserService userService;

    public EnrollmentResponseDto createEnroll(EnrollmentRequestDto enrollmentRequestDto) {
        Optional<Enrollment> optionalEnrollment
                = enrollmentRepository.findEnrollmentByCourse_IdAndUser_Id(enrollmentRequestDto.getCourseId(),
                enrollmentRequestDto.getUserId());

        if (optionalEnrollment.isPresent()) {
            throw new UnsupportedOperationException("Запись на указанный курс уже существует для пользователя.");
        }

        User findedUser = userService.getUserEntityById(enrollmentRequestDto.getUserId());
        if (!findedUser.getRole().equals(Role.STUDENT)) {
            throw new UnsupportedOperationException("На курс могут записаться пользователи с ролью STUDENT.");
        }

        Course findedCourse = courseService.getCourseEntityById(enrollmentRequestDto.getCourseId());

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(findedCourse);
        enrollment.setUser(findedUser);
        enrollment.setStatus(EnrollStatus.COMPLETED);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentResponseDto.builder()
                .id(savedEnrollment.getId())
                .enrollDate(savedEnrollment.getEnrollDate())
                .status(savedEnrollment.getStatus().toString())
                .build();
    }

    public void deleteEnrollById(Integer enrollId) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollId);
        if (optionalEnrollment.isEmpty()) {
            throw new EntityNotFoundException("Запись на курс с указанным ID не найдена.");
        }

        enrollmentRepository.delete(optionalEnrollment.get());
    }
}
