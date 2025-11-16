package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mephi.learningplatformservice.dto.request.CourseRequestDto;
import ru.mephi.learningplatformservice.dto.response.CourseResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.CourseMapper;
import ru.mephi.learningplatformservice.model.Category;
import ru.mephi.learningplatformservice.model.Course;
import ru.mephi.learningplatformservice.model.User;
import ru.mephi.learningplatformservice.model.enums.Role;
import ru.mephi.learningplatformservice.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CourseMapper courseMapper;

    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        Category findedCategory = categoryService.getCategoryEntityById(courseRequestDto.getCategoryId());
        User findedUser = userService.getUserEntityById(courseRequestDto.getTeacherId());
        if (!findedUser.getRole().equals(Role.TEACHER)) {
            throw new UnsupportedOperationException("Указанный пользователь не обладает ролью TEACHER.");
        }

        Course course = courseMapper.toCourse(courseRequestDto);
        course.setCategory(findedCategory);
        course.setTeacher(findedUser);

        return courseMapper.toCourseResponseDto(courseRepository.save(course));
    }

    public Course getCourseEntityById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Курс с указанным ID не найден."));
    }

    public CourseResponseDto updateCourseById(Integer courseId, CourseRequestDto courseRequestDto) {
        Course course = getCourseEntityById(courseId);
        Course updatedCourse = courseMapper.toCourse(course, courseRequestDto);

        return courseMapper.toCourseResponseDto(courseRepository.save(updatedCourse));
    }

    @Transactional
    public void deleteCourseById(Integer courseId) {
        Course course = getCourseEntityById(courseId);
        courseRepository.delete(course);
    }

    public List<CourseResponseDto> getUserCourses(Integer userId) {
        User user = userService.getUserEntityById(userId);
        userService.checkUserIsAdminOrStudent(user.getId());

        return courseRepository.getCoursesByUserId(userId).stream()
                .map(courseMapper::toCourseResponseDto)
                .toList();
    }

    public CourseResponseDto getCourseById(Integer courseId) {
        return courseMapper.toCourseResponseDto(getCourseEntityById(courseId));
    }
}
