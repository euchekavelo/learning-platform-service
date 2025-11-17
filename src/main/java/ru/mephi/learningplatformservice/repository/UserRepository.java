package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value =
            "SELECT us.* " +
            "FROM users us" +
            "INNER JOIN enrollments es ON us.id = es.user_id" +
            "WHERE es.course_id = :courseId", nativeQuery = true)
    List<User> getStudentsByCourseId(Integer courseId);
}
