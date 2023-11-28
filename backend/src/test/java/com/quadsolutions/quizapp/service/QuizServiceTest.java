package com.quadsolutions.quizapp.service;

import com.quadsolutions.quizapp.domain.Question;
import com.quadsolutions.quizapp.domain.QuestionDifficulty;
import com.quadsolutions.quizapp.domain.QuestionType;
import com.quadsolutions.quizapp.domain.Quiz;
import com.quadsolutions.quizapp.exception.NotFoundException;
import com.quadsolutions.quizapp.repository.QuizRepository;
import com.quadsolutions.quizapp.rest.dto.CheckAnswerRequest;
import com.quadsolutions.quizapp.service.impl.QuizService;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuizServiceTest {
    @Mock
    private QuizRepository quizRepository;

    @InjectMocks
    private QuizService quizService;

    @Test
    void checkAnswerQuizNotFound() {
        // Arrange
        UUID quizId = UUID.randomUUID();
        CheckAnswerRequest request = CheckAnswerRequest.builder().quizId(quizId).build();

        // Act en Assert
        assertThrows(NotFoundException.class, () -> quizService.checkAnswer(request));

        verify(quizRepository, times(1)).get(request.getQuizId());
        verify(quizRepository, never()).delete(quizId);
    }

    @Test
    void checkAnswerLastQuestionDeleteQuiz() {
        // Arrange
        UUID quizId = UUID.randomUUID();
        Question question = Question.builder()
                .type(QuestionType.BOOLEAN)
                .difficulty(QuestionDifficulty.EASY)
                .category("Test")
                .correctAnswer("True")
                .answers(Collections.singletonList("True"))
                .build();

        Quiz quiz = Quiz.
                builder().
                id(quizId).
                questions(Collections.singletonList(question)).
                build();

        when(quizRepository.get(quizId)).thenReturn(quiz);

        CheckAnswerRequest request = CheckAnswerRequest.builder()
                .quizId(quizId)
                .questionIndex(0)
                .build();

        // Act en Asset
        quizService.checkAnswer(request);

        // check that repository quizzes size is 0

        assertEquals( quizRepository.getAll().size(), 0);
        verify(quizRepository, times(1)).get(request.getQuizId());
        verify(quizRepository, times(1)).delete(request.getQuizId());
    }
}
