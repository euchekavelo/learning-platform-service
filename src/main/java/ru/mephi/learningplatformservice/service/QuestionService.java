package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.QuestionRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuestionResponseDto;
import ru.mephi.learningplatformservice.mapper.QuestionMapper;
import ru.mephi.learningplatformservice.model.Question;
import ru.mephi.learningplatformservice.model.Quiz;
import ru.mephi.learningplatformservice.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final UserService userService;
    private final QuizService quizService;

    public QuestionResponseDto addQuestion(QuestionRequestDto questionRequestDto) {
        userService.checkUserIsAdminOrTeacher(questionRequestDto.getUserId());

        Quiz findedQuiz = quizService.getQuizEntityById(questionRequestDto.getQuizId());
        Question question = questionMapper.toModel(questionRequestDto);
        question.setQuiz(findedQuiz);

        return questionMapper.toQuizResponseDto(questionRepository.save(question));
    }

    public Question getQuestionEntityById(Integer questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("вопрос с указанным ID не найден."));
    }
}
