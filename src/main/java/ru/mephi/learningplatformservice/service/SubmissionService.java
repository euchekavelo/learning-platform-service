package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.learningplatformservice.dto.request.EvaluateRequestDto;
import ru.mephi.learningplatformservice.dto.request.SubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.SubmissionResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.SubmissionMapper;
import ru.mephi.learningplatformservice.model.Assignment;
import ru.mephi.learningplatformservice.model.Submission;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.repository.SubmissionRepository;

import java.util.List;

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

    public SubmissionResponseDto evaluateSubmission(Integer submissionId, EvaluateRequestDto evaluateRequestDto) {
        userService.checkUserIsAdminOrTeacher(evaluateRequestDto.getUserId());
        Submission findedSubmission = findSubmissionEntityById(submissionId);
        findedSubmission.setScore(evaluateRequestDto.getScore());
        findedSubmission.setFeedback(evaluateRequestDto.getFeedback());

        return submissionMapper.toSubmissionResponseDto(submissionRepository.save(findedSubmission));
    }

    public Submission findSubmissionEntityById(Integer submissionId) {
        return submissionRepository.findById(submissionId)
                .orElseThrow(() -> new EntityNotFoundException("Решение с указанным идентификатором не найдено."));
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponseDto> getListOfSubmissionToAssignment(Integer assignmentId, Integer teacherId) {
        userService.checkUserIsAdminOrTeacher(teacherId);
        List<Submission> submissions = assignmentService.getAssignmentEntityById(assignmentId).getSubmissions();

        return submissions.stream()
                .map(submissionMapper::toSubmissionResponseDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponseDto> getListOfUserSubmissions(Integer userId) {
        User findedUser = userService.getUserEntityById(userId);
        if (!findedUser.getRole().equals(Role.STUDENT)) {
            throw new UnsupportedOperationException("Пользователь должен обладать ролью STUDENT.");
        }

        return findedUser.getSubmissions().stream()
                .map(submissionMapper::toSubmissionResponseDto)
                .toList();
    }
}
