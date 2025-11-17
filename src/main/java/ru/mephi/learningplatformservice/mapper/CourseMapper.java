package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.CourseRequestDto;
import ru.mephi.learningplatformservice.dto.response.CourseResponseDto;
import ru.mephi.learningplatformservice.model.Course;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    Course toCourse(CourseRequestDto courseRequestDto);

    CourseResponseDto toCourseResponseDto(Course course);

    Course toCourse(@MappingTarget Course course, CourseRequestDto courseRequestDto);
}
