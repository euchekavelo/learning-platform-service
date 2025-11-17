package ru.mephi.learningplatformservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import ru.mephi.learningplatformservice.dto.request.SubmissionRequestDto;
import ru.mephi.learningplatformservice.dto.response.SubmissionResponseDto;
import ru.mephi.learningplatformservice.model.Submission;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SubmissionMapper {

    Submission toSubmission(SubmissionRequestDto submissionRequestDto);

    @Mapping(source = "student.id", target = "studentId")
    SubmissionResponseDto toSubmissionResponseDto(Submission submission);
}
