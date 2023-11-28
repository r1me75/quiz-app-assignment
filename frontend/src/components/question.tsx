import axios from "axios";
import classNames from "classnames";
import { useContext, useState, useCallback, useEffect } from "react";
import { QuizContext, BASE_URL } from "../App";

export default function Question() {
  const {
    currentQuestion,
    currentQuestionIndex,
    quiz,
    correctAnswers,
    setShowNextQuestion,
    setCorrectAnswers,
  } = useContext(QuizContext);

  const [answerResponse, setAnswerResponse] =
    useState<CheckAnswerReponse | null>(null);

  const checkAnswer = useCallback(
    async (index: number) => {
      try {
        const res = await axios.post(BASE_URL + "/check-answer", {
          quizId: quiz?.id,
          questionIndex: currentQuestionIndex,
          answer: currentQuestion?.answers[index],
        });
        const data = res.data as CheckAnswerReponse;
        setAnswerResponse(data);

        if (data.correct) {
          setCorrectAnswers(correctAnswers + 1);
        }
      } catch (ex) {
        console.log(ex);
        alert("Something went wrong, please try again later!");
      }
    },
    [quiz, currentQuestionIndex, currentQuestion]
  );

  useEffect(() => {
    if (answerResponse) {
      setShowNextQuestion(true);
    }
  }, [answerResponse]);

  useEffect(() => {
    setAnswerResponse(null);
  }, [currentQuestionIndex]);

  return (
    <div
      className={classNames("flex items-center justify-center gap-3 w-full", {
        "flex-row px-3": currentQuestion?.type == "boolean",
        "flex-col": currentQuestion?.type == "multiple",
      })}
    >
      {currentQuestion!.answers.map((answer, i) => (
        <button
          disabled={answerResponse != null}
          onClick={() => checkAnswer(i)}
          key={`${answer}-${i}`}
          className={classNames(
            "py-2 uppercase border border-slate-300 text-blue-500 font-medium rounded",
            {
              "w-full flex-1": currentQuestion?.type == "boolean",
              "w-11/12 ex-1": currentQuestion?.type == "multiple",
              "bg-red-500 text-white hover:bg-red-500":
                answerResponse?.answer == answer &&
                answerResponse?.correct == false,
              "bg-green-500 text-white hover:bg-green-500":
                answerResponse?.correctAnswer == answer,
              " hover:bg-blue-500  hover:text-white": answerResponse == null,
            }
          )}
        >
          {answer}
        </button>
      ))}
    </div>
  );
}
