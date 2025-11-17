package ru.mephi.learningplatformservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.mephi.learningplatformservice.config.PostgreSQLContainerConfig;
import ru.mephi.learningplatformservice.dto.request.CourseRequestDto;
import ru.mephi.learningplatformservice.dto.request.EnrollmentRequestDto;
import ru.mephi.learningplatformservice.dto.response.CourseResponseDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PostgreSQLContainerConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class EnrollmentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEnrollmentFailure() throws Exception {
        EnrollmentRequestDto enrollmentRequestDto = new EnrollmentRequestDto();
        enrollmentRequestDto.setCourseId(1);
        enrollmentRequestDto.setUserId(2);

        mockMvc.perform(post("/api/enrollments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(enrollmentRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateEnrollmentSuccess() throws Exception {
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setTitle("Test Title");
        courseRequestDto.setDescription("Test Description");
        courseRequestDto.setCategoryId(1);
        courseRequestDto.setTeacherId(1);

        MvcResult result = mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDto)))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        CourseResponseDto courseResponseDto = objectMapper.readValue(response, CourseResponseDto.class);

        EnrollmentRequestDto enrollmentRequestDto = new EnrollmentRequestDto();
        enrollmentRequestDto.setCourseId(courseResponseDto.getId());
        enrollmentRequestDto.setUserId(2);

        mockMvc.perform(post("/api/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enrollmentRequestDto)))
                .andExpect(status().isCreated());
    }
}
