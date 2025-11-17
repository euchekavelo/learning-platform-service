package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.QuizRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuizResponseDto;
import ru.mephi.learningplatformservice.model.Quiz;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizMapper {

    Quiz toQuiz(QuizRequestDto quizRequestDto);

    QuizResponseDto toQuizResponseDto(Quiz quiz);
}
