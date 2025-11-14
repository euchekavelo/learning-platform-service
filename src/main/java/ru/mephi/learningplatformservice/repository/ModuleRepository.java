package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
