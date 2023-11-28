package com.quadsolutions.quizapp.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckAnswerResponse {
    private String question;
    private String answer;
    private String correctAnswer;
    private boolean correct;
}
