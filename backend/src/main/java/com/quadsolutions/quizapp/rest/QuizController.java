package com.quadsolutions.quizapp.rest;

import com.quadsolutions.quizapp.rest.dto.CheckAnswerRequest;
import com.quadsolutions.quizapp.rest.dto.CheckAnswerResponse;
import com.quadsolutions.quizapp.rest.dto.QuizResponse;
import com.quadsolutions.quizapp.service.IQuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final IQuizService quizService;

    @GetMapping("/start")
    public ResponseEntity<QuizResponse> startQuiz() {
        return ResponseEntity.ok(quizService.startQuiz());
    }

    @PostMapping("/check-answer")
    public ResponseEntity<CheckAnswerResponse> checkAnswer(@Valid @RequestBody  CheckAnswerRequest checkAnswerRequest) {
        return new ResponseEntity<>(quizService.checkAnswer(checkAnswerRequest), HttpStatus.CREATED);
    }
}
