package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
