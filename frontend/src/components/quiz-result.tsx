import { useContext, useState } from "react";
import { QuizContext } from "../App";

export default function QuizResult() {
  const { quiz, correctAnswers, resetQuiz } = useContext(QuizContext);

  return (
    <div className="flex flex-col items-center justify-center h-full gap-3">
      <p>Quiz completed! You have scored...</p>
      <p>
        <span className="font-bold text-5xl">{correctAnswers}</span> /{" "}
        {quiz?.questions.length}
      </p>

      <p>Click the button to restart the quiz and try again!</p>

      <button
        onClick={resetQuiz}
        className="px-4 py-2 bg-blue-500 hover:bg-blue-700 text-white font-medium rounded"
      >
        Back to Start
      </button>
    </div>
  );
}
