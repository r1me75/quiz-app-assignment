package com.quadsolutions.quizapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuestionDifficulty {
    @JsonProperty("easy")
    EASY,
    @JsonProperty("medium")
    MEDIUM,
    @JsonProperty("hard")
    HARD
}
