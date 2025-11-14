package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query(value =
            "SELECT cs.* " +
            "FROM courses cs" +
            "INNER JOIN enrollments es ON cs.id = es.course_id" +
            "WHERE es.user_id = :userId", nativeQuery = true)
    List<Course> getCoursesByUserId(Integer userId);
}
