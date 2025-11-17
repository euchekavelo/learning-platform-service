package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.QuizSubmission;
import ru.mephi.learningplatformservice.model.User;

import java.util.List;

@Repository
public interface QuizSubmissionRepository extends JpaRepository<QuizSubmission, Integer> {

    List<QuizSubmission> findByStudent(User user);
}
