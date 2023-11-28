package com.quadsolutions.quizapp.repository;

import com.quadsolutions.quizapp.domain.Quiz;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class QuizRepository {
    private Map<UUID, Quiz> quizzes = new HashMap<>();

    public void save(Quiz quiz) {
        UUID id = UUID.randomUUID();
        quiz.setId(id);
        quizzes.put(id, quiz);
    }

    public Quiz get(UUID id) {
        Quiz quiz = quizzes.get(id);
        return quizzes.get(id);
    }

    public Map<UUID, Quiz> getAll() {
        return quizzes;
    }

    public void delete(UUID id) {
        quizzes.remove(id);
    }
}
