package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
