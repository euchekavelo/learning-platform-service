package ru.mephi.learningplatformservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mephi.learningplatformservice.model.AnswerOption;
import ru.mephi.learningplatformservice.projection.AnswerOptionProjection;

import java.util.List;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption, Integer> {

    @Query(value =
            "SELECT qs.id AS questionId, aos.id AS answerOptionId " +
            "FROM quizes qes " +
            "INNER JOIN questions qs ON qs.quiz_id = qes.id " +
            "INNER JOIN answer_options aos ON aos.question_id = qs.id " +
            "WHERE aos.is_correct = TRUE AND qes.id = :quizId", nativeQuery = true)
    List<AnswerOptionProjection> getCorrectAnswerOptionsByQuizId(Integer quizId);
}
