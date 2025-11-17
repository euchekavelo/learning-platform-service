package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class QuestionResponseDto {

    private Integer id;
    private String text;
    private String type;
}
