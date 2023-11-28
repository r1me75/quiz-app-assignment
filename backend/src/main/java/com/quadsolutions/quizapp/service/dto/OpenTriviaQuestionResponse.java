package com.quadsolutions.quizapp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.quadsolutions.quizapp.domain.QuestionDifficulty;
import com.quadsolutions.quizapp.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenTriviaQuestionResponse {
    private QuestionType type;
    private QuestionDifficulty difficulty;
    private String category;
    private String question;

    @JsonProperty("correct_answer")
    private String correctAnswer;

    @JsonProperty("incorrect_answers")
    private List<String> incorrectAnswers;
}
