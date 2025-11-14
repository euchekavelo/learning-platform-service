package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.AssignmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.AssignmentResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.AssignmentMapper;
import ru.mephi.learningplatformservice.model.Assignment;
import ru.mephi.learningplatformservice.model.Lesson;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.repository.AssignmentRepository;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;
    private final UserService userService;
    private final LessonService lessonService;

    public AssignmentResponseDto addAssignment(AssignmentRequestDto assignmentRequestDto) {
        User findedUser = userService.getUserEntityById(assignmentRequestDto.getUserId());
        if (!findedUser.getRole().equals(Role.TEACHER)) {
            throw new UnsupportedOperationException("Пользователь должен обладать ролью TEACHER.");
        }

        Lesson findedLesson = lessonService.getLessonEntityById(assignmentRequestDto.getLessonId());
        Assignment assignment = assignmentMapper.toAssignment(assignmentRequestDto);
        assignment.setLesson(findedLesson);

        return assignmentMapper.toAssignmentResponseDto(assignmentRepository.save(assignment));
    }

    public Assignment getAssignmentEntityById(Integer id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Задание с указанным ID не найдено."));
    }
}
