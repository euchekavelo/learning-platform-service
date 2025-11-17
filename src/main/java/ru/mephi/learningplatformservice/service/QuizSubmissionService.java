package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.learningplatformservice.dto.request.QuizSubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuizSubmissionResponseDto;
import ru.mephi.learningplatformservice.mapper.QuizSubmissionMapper;
import ru.mephi.learningplatformservice.model.Quiz;
import ru.mephi.learningplatformservice.model.QuizSubmission;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.projection.AnswerOptionProjection;
import ru.mephi.learningplatformservice.repository.QuizSubmissionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizSubmissionService {

    private final QuizSubmissionRepository quizSubmissionRepository;
    private final QuizSubmissionMapper quizSubmissionMapper;
    private final UserService userService;
    private final QuizService quizService;
    private final AnswerOptionService answerOptionService;

    @Transactional
    public QuizSubmissionResponseDto takeQuiz(QuizSubmissionRequestDto quizSubmissionRequestDto) {
        User findedUser = userService.getUserEntityById(quizSubmissionRequestDto.getStudentId());
        if (!findedUser.getRole().equals(Role.STUDENT)) {
            throw new UnsupportedOperationException("Пользователь должен обладать ролью STUDENT.");
        }

        Quiz quiz = quizService.getQuizEntityById(quizSubmissionRequestDto.getQuizId());
        int score = 0;
        Map<Integer , Integer> studentAnswers = quizSubmissionRequestDto.getAnswers();
        List<AnswerOptionProjection> correctAnswers = answerOptionService.getCorrectAnswersByQuizId(quiz.getId());

        for (AnswerOptionProjection correctAnswer : correctAnswers) {
            Integer studentAnswer = studentAnswers.get(correctAnswer.getQuestionId());
            if (studentAnswer != null && studentAnswer.equals(correctAnswer.getAnswerOptionId())) {
                score++;
            }
        }

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setQuiz(quiz);
        quizSubmission.setScore(score);
        quizSubmission.setStudent(findedUser);

        return quizSubmissionMapper.toQuizSubmissionResponseDto(quizSubmissionRepository.save(quizSubmission));
    }

    public List<QuizSubmissionResponseDto> getListOfUserQuizSubmissions(Integer userId) {
        User findedUser = userService.getUserEntityById(userId);
        List<QuizSubmission> quizSubmissions = quizSubmissionRepository.findByStudent(findedUser);

        return quizSubmissions.stream()
                .map(quizSubmissionMapper::toQuizSubmissionResponseDto)
                .collect(Collectors.toList());
    }
}
