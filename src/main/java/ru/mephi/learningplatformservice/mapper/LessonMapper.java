package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.LessonComponentRequestDto;
import ru.mephi.learningplatformservice.dto.response.LessonComponentResponseDto;
import ru.mephi.learningplatformservice.model.Lesson;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LessonMapper {

    List<Lesson> toLessons(List<LessonComponentRequestDto> lessonComponentRequestDtoList);

    List<LessonComponentResponseDto> toLessonComponentResponseDtoList(List<Lesson> lessons);
}
