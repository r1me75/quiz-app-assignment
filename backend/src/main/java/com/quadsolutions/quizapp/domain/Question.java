package com.quadsolutions.quizapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private QuestionType type;
    private QuestionDifficulty difficulty;
    private String category;
    private String question;
    private String correctAnswer;
    private List<String> answers;
}
