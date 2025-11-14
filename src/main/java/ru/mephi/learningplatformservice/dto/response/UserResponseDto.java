package ru.mephi.learningplatformservice.dto.response;

import lombok.Data;

@Data
public class UserResponseDto {

    private Integer id;
    private String username;
    private String role;
}
