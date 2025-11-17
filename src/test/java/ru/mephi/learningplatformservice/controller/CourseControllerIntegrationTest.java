package ru.mephi.learningplatformservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.mephi.learningplatformservice.config.PostgreSQLContainerConfig;
import ru.mephi.learningplatformservice.dto.request.CourseRequestDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = PostgreSQLContainerConfig.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCourseSuccess() throws Exception {
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setTitle("Test Title");
        courseRequestDto.setDescription("Test Description");
        courseRequestDto.setCategoryId(1);
        courseRequestDto.setTeacherId(1);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCourseByIdSuccess() throws Exception {
        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").isNotEmpty())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    void testGetCourseByIdFailure() throws Exception {
        mockMvc.perform(get("/api/courses/232134"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCourseCategoryFailure() throws Exception {
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setTitle("Test Title1");
        courseRequestDto.setDescription("Test Description1");
        courseRequestDto.setCategoryId(3213123);
        courseRequestDto.setTeacherId(1);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDto)))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCourseUserFailure() throws Exception {
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setTitle("Test Title2");
        courseRequestDto.setDescription("Test Description2");
        courseRequestDto.setCategoryId(1);
        courseRequestDto.setTeacherId(131123);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDto)))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateCourseUserIsStudentFailure() throws Exception {
        CourseRequestDto courseRequestDto = new CourseRequestDto();
        courseRequestDto.setTitle("Test Title3");
        courseRequestDto.setDescription("Test Description3");
        courseRequestDto.setCategoryId(1);
        courseRequestDto.setTeacherId(2);

        mockMvc.perform(post("/api/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseRequestDto)))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.result").value(false))
                .andExpect(status().isBadRequest());
    }
}
