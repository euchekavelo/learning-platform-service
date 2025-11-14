package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
