package com.quadsolutions.quizapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuestionType {
    @JsonProperty("multiple")
    MULTIPLE,
    @JsonProperty("boolean")
    BOOLEAN
}
