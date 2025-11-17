package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.response.QuizSubmissionResponseDto;
import ru.mephi.learningplatformservice.model.QuizSubmission;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizSubmissionMapper {

    @Mapping(source = "quiz.id", target = "quizId")
    QuizSubmissionResponseDto toQuizSubmissionResponseDto(QuizSubmission quizSubmission);
}
