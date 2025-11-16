package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.AnswerRequestDto;
import ru.mephi.learningplatformservice.dto.response.AnswerResponseDto;
import ru.mephi.learningplatformservice.mapper.AnswerOptionMapper;
import ru.mephi.learningplatformservice.model.AnswerOption;
import ru.mephi.learningplatformservice.model.Question;
import ru.mephi.learningplatformservice.projection.AnswerOptionProjection;
import ru.mephi.learningplatformservice.repository.AnswerOptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerOptionService {

    private final AnswerOptionRepository answerOptionRepository;
    private final AnswerOptionMapper answerOptionMapper;
    private final UserService userService;
    private final QuestionService questionService;

    public AnswerResponseDto addAnswerOption(AnswerRequestDto answerRequestDto) {
        userService.checkUserIsAdminOrTeacher(answerRequestDto.getTeacherId());

        Question findedQuestion = questionService.getQuestionEntityById(answerRequestDto.getQuestionId());
        AnswerOption answerOption = answerOptionMapper.toAnswerOption(answerRequestDto);
        answerOption.setQuestion(findedQuestion);

        return answerOptionMapper.toAnswerResponseDto(answerOptionRepository.save(answerOption));
    }

    public List<AnswerOptionProjection> getCorrectAnswersByQuizId(Integer id) {
        return answerOptionRepository.getCorrectAnswerOptionsByQuizId(id);
    }
}
