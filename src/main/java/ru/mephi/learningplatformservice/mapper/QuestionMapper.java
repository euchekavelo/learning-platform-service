package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.QuestionRequestDto;
import ru.mephi.learningplatformservice.dto.response.QuestionResponseDto;
import ru.mephi.learningplatformservice.model.Question;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    Question toModel(QuestionRequestDto questionRequestDto);

    QuestionResponseDto toQuizResponseDto(Question question);
}
