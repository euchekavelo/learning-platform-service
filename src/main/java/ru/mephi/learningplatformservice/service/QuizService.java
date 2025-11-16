package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.QuizRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuizResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.QuizMapper;
import ru.mephi.learningplatformservice.model.Module;
import ru.mephi.learningplatformservice.model.Quiz;
import ru.mephi.learningplatformservice.repository.QuizRepository;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final UserService userService;
    private final ModuleService moduleService;

    public QuizResponseDto addQuiz(QuizRequestDto quizRequestDto) {
        userService.checkUserIsAdminOrTeacher(quizRequestDto.getUserId());

        Module findedModule = moduleService.getModuleEntityById(quizRequestDto.getModuleId());
        Quiz quiz = quizMapper.toQuiz(quizRequestDto);
        quiz.setModule(findedModule);

        return quizMapper.toQuizResponseDto(quizRepository.save(quiz));
    }

    public Quiz getQuizEntityById(Integer quizId) {
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new EntityNotFoundException("тест с указанным ID не найден."));
    }
}
