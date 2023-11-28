import { useContext } from "react";
import { QuizContext } from "../App";
import Question from "./question";
import classNames from "classnames";
import ArrowRight from "./right-arrow";

export default function QuestionContainer() {
  const {
    currentQuestion,
    currentQuestionIndex,
    quiz,
    showNextQuestion,
    setCurrentQuestionIndex,
    setShowNextQuestion,
  } = useContext(QuizContext);

  if (currentQuestion == null) {
    return null;
  }

  const handleClick = () => {
    setCurrentQuestionIndex(currentQuestionIndex + 1);
    setShowNextQuestion(false);
  };

  return (
    <>
      <div className="flex flex-col items-center justify-center h-full gap-3">
        <p
          dangerouslySetInnerHTML={{ __html: currentQuestion.question }}
          className="font-medium text-2xl text-center mx-5"
        ></p>
        <p className="mb-5">
          Question {currentQuestionIndex + 1} / {quiz?.questions.length}
        </p>

        <Question />
      </div>

      <div className="mx-5 flex flex-row justify-between items-center">
        <div
          className={classNames(
            "capitalize w-28 text-center py-1 text-sm font-medium bg-red-500 rounded-full text-white",
            {
              "bg-red-500": currentQuestion.difficulty == "hard",
              "bg-yellow-500": currentQuestion.difficulty == "medium",
              "bg-green-500": currentQuestion.difficulty == "easy",
            }
          )}
        >
          {currentQuestion.difficulty}
        </div>

        {showNextQuestion ? (
          <button
            onClick={handleClick}
            className="py-1 bg-blue-500 hover:bg-blue-700 text-white px-4 rounded text-sm font-medium flex items-center gap-2"
          >
            Next Question
            <ArrowRight />
          </button>
        ) : null}
      </div>
    </>
  );
}
