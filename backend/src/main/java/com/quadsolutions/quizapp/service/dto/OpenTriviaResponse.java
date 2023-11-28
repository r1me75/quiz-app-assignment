package com.quadsolutions.quizapp.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpenTriviaResponse {
    @JsonProperty("response_code")
    private int responseCode;
    private List<OpenTriviaQuestionResponse> results;
}
