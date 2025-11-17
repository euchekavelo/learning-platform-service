package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
}
