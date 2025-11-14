package ru.mephi.learningplatformservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mephi.learningplatformservice.dto.request.LessonRequestDto;
import ru.mephi.learningplatformservice.dto.response.LessonComponentResponseDto;
import ru.mephi.learningplatformservice.dto.response.LessonResponseDto;
import ru.mephi.learningplatformservice.exception.EntityNotFoundException;
import ru.mephi.learningplatformservice.mapper.LessonMapper;
import ru.mephi.learningplatformservice.model.Lesson;
import ru.mephi.learningplatformservice.model.Module;
import ru.mephi.learningplatformservice.repository.LessonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleService moduleService;
    private final LessonMapper lessonMapper;

    public LessonResponseDto addLessons(LessonRequestDto lessonRequestDto) {
        Module findedModule = moduleService.getModuleEntityById(lessonRequestDto.getModuleId());
        List<Lesson> lessons = lessonMapper.toLessons(lessonRequestDto.getNewLessons());
        List<Lesson> savedLessons = lessonRepository.saveAll(lessons);
        List<LessonComponentResponseDto> lessonComponentResponseDtoList
                = lessonMapper.toLessonComponentResponseDtoList(savedLessons);

        return LessonResponseDto.builder()
                .moduleId(findedModule.getId())
                .newLessons(lessonComponentResponseDtoList)
                .build();
    }

    public Lesson getLessonEntityById(Integer id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Урок с указанным ID не найден."));
    }
}
