package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.AnswerRequestDto;
import ru.mephi.learningplatformservice.dto.response.AnswerResponseDto;
import ru.mephi.learningplatformservice.model.AnswerOption;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AnswerOptionMapper {

    AnswerOption toAnswerOption(AnswerRequestDto answerRequestDto);

    AnswerResponseDto toAnswerResponseDto(AnswerOption answerOption);
}
