package com.quadsolutions.quizapp.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckAnswerRequest {
    @NotNull(message = "Quiz ID is required")
    private UUID quizId;
    @NotNull(message = "Question index is required")
    private int questionIndex;
    @NotNull(message = "Answer is required")
    private String answer;
}
