package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.AssignmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.AssignmentResponseDto;
import ru.mephi.learningplatformservice.model.Assignment;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssignmentMapper {

    Assignment toAssignment(AssignmentRequestDto assignmentRequestDto);

    AssignmentResponseDto toAssignmentResponseDto(Assignment assignment);
}
