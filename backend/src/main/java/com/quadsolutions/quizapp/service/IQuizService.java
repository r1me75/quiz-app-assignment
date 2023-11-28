package com.quadsolutions.quizapp.service;


import com.quadsolutions.quizapp.rest.dto.CheckAnswerRequest;
import com.quadsolutions.quizapp.rest.dto.CheckAnswerResponse;
import com.quadsolutions.quizapp.rest.dto.QuizResponse;
import com.quadsolutions.quizapp.service.dto.OpenTriviaResponse;

public interface IQuizService {
    QuizResponse startQuiz();
    CheckAnswerResponse checkAnswer(CheckAnswerRequest checkAnswerRequest);
}
