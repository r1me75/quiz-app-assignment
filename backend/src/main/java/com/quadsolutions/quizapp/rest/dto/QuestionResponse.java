package com.quadsolutions.quizapp.rest.dto;

import com.quadsolutions.quizapp.domain.QuestionDifficulty;
import com.quadsolutions.quizapp.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {
    private QuestionType type;
    private QuestionDifficulty difficulty;
    private String category;
    private String question;
    private List<String> answers;
}
