package ru.mephi.learningplatformservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String message;
    private Boolean result;
}
