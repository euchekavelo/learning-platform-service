package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.SubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.SubmissionResponseDto;
import ru.mephi.learningplatformservice.mapper.SubmissionMapper;
import ru.mephi.learningplatformservice.model.Assignment;
import ru.mephi.learningplatformservice.model.Submission;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.repository.SubmissionRepository;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final UserService userService;
    private final AssignmentService assignmentService;

    public SubmissionResponseDto sendSubmission(SubmissionRequestDto submissionRequestDto) {
        User findedUser = userService.getUserEntityById(submissionRequestDto.getUserId());
        if (!findedUser.getRole().equals(Role.STUDENT)) {
            throw new UnsupportedOperationException("Пользователь должен обладать ролью STUDENT.");
        }

        Assignment findedAssignment = assignmentService.getAssignmentEntityById(submissionRequestDto.getAssignmentId());
        Submission submission = submissionMapper.toSubmission(submissionRequestDto);
        submission.setAssignment(findedAssignment);
        submission.setStudent(findedUser);

        return submissionMapper.toSubmissionResponseDto(submissionRepository.save(submission));
    }
}
