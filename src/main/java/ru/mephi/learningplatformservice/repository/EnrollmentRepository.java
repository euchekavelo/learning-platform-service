package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Course;
import ru.mephi.learningplatformservice.model.Enrollment;
import ru.mephi.learningplatformservice.model.User;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    Optional<Enrollment> findEnrollmentByCourse_IdAndUser_Id(Integer courseId, Integer userId);
}
