package com.quadsolutions.quizapp.service.impl;

import com.quadsolutions.quizapp.domain.Question;
import com.quadsolutions.quizapp.domain.Quiz;
import com.quadsolutions.quizapp.exception.NotFoundException;
import com.quadsolutions.quizapp.repository.QuizRepository;
import com.quadsolutions.quizapp.rest.dto.CheckAnswerRequest;
import com.quadsolutions.quizapp.rest.dto.CheckAnswerResponse;
import com.quadsolutions.quizapp.rest.dto.QuestionResponse;
import com.quadsolutions.quizapp.rest.dto.QuizResponse;
import com.quadsolutions.quizapp.service.IQuizService;
import com.quadsolutions.quizapp.service.dto.OpenTriviaQuestionResponse;
import com.quadsolutions.quizapp.service.dto.OpenTriviaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements IQuizService {
    private final RestTemplate restTemplate;
    private final QuizRepository quizRepository;

    @Value("${opentrivia.api.url}")
    private String apiUrl;

    @Override
    public QuizResponse startQuiz() {
        List<Question> questions = getQuestionsFromApi();
        Quiz quiz = Quiz.builder()
                .questions(questions)
                .build();

        quizRepository.save(quiz);

        return QuizResponse.builder()
                .id(quiz.getId())
                .questions(questions.stream().map(this::mapToQuestionResponse).toList())
                .build();
    }

    @Override
    public CheckAnswerResponse checkAnswer(CheckAnswerRequest checkAnswerRequest) {
        Quiz quiz = quizRepository.get(checkAnswerRequest.getQuizId());
        if (quiz == null) {
            throw new NotFoundException("Quiz not found");
        }

        Question question = quiz.getQuestions().get(checkAnswerRequest.getQuestionIndex());
        if (question == null) {
            throw new NotFoundException("Question not found");
        }

        // Verwijder quiz bij laatste vraag
        if(checkAnswerRequest.getQuestionIndex() == quiz.getQuestions().size() - 1) {
            quizRepository.delete(quiz.getId());
        }

        return CheckAnswerResponse.builder()
                .question(question.getQuestion())
                .correct(question.getCorrectAnswer().equals(checkAnswerRequest.getAnswer()))
                .correctAnswer(question.getCorrectAnswer())
                .answer(checkAnswerRequest.getAnswer())
                .build();
    }

    private List<Question> getQuestionsFromApi() {
        OpenTriviaResponse response = restTemplate.getForObject(apiUrl, OpenTriviaResponse.class);

        if (response == null) {
            throw new NotFoundException("No questions found");
        }

        return response.getResults().stream().map(this::mapToQuestion).toList();
    }

    private QuestionResponse mapToQuestionResponse(Question question) {
        return QuestionResponse.builder()
                .type(question.getType())
                .difficulty(question.getDifficulty())
                .category(question.getCategory())
                .question(question.getQuestion())
                .answers(question.getAnswers())
                .build();
    }

    private Question mapToQuestion(OpenTriviaQuestionResponse openTriviaQuestionResponse) {
        List<String> answers = openTriviaQuestionResponse.getIncorrectAnswers();
        answers.add(openTriviaQuestionResponse.getCorrectAnswer());

        // Shuffle want anders is de correcte antwoord altijd op dezelfde plaats
        Collections.shuffle(answers);

        return Question.builder()
                .type(openTriviaQuestionResponse.getType())
                .difficulty(openTriviaQuestionResponse.getDifficulty())
                .category(openTriviaQuestionResponse.getCategory())
                .question(openTriviaQuestionResponse.getQuestion())
                .correctAnswer(openTriviaQuestionResponse.getCorrectAnswer())
                .answers(answers)
                .build();
    }

}
