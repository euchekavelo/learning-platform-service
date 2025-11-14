package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
}
