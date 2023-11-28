import { createContext, useState } from "react";
import QuestionContainer from "./components/question-container";
import StartQuizButton from "./components/start-quiz-button";
import QuizResult from "./components/quiz-result";

export const BASE_URL = "http://localhost:8080/api/quiz";

export const initialContextState: QuizContextProps = {
  quiz: null,
  currentQuestionIndex: 0,
  currentQuestion: null,
  showNextQuestion: false,
  correctAnswers: 0,

  setQuiz: () => {},
  setCurrentQuestionIndex: () => {},
  setShowNextQuestion: () => {},
  setCorrectAnswers: () => {},
  resetQuiz: () => {},
};

export const QuizContext = createContext<QuizContextProps>(initialContextState);

function App() {
  // TODO: gebruik useReducer;
  const [quiz, setQuiz] = useState<Quiz | null>(null);
  const [currentQuestionIndex, setCurrentQuestionIndex] = useState<number>(0);
  const [showNextQuestion, setShowNextQuestion] = useState<boolean>(false);
  const [correctAnswers, setCorrectAnswers] = useState<number>(0);

  const currentQuestion = quiz?.questions[currentQuestionIndex];

  const resetQuiz = () => {
    setQuiz(null);
    setCurrentQuestionIndex(0);
    setShowNextQuestion(false);
    setCorrectAnswers(0);
  };

  return (
    <div className="w-screen h-screen flex flex-col items-center justify-center">
      <div className="flex flex-col items-center justify-center pt-10">
        <img src="/logo.svg" alt="QUAD" width="133" height="35" />

        <p className="uppercase mt-2 text-xl">Technical Assignment</p>
      </div>

      <QuizContext.Provider
        value={{
          quiz,
          currentQuestionIndex,
          currentQuestion: currentQuestion ?? null,
          showNextQuestion,
          correctAnswers,

          setQuiz,
          setCurrentQuestionIndex,
          setShowNextQuestion,
          setCorrectAnswers,
          resetQuiz,
        }}
      >
        <div className="md:w-[800px] mt-10 w-11/12 py-10 mx-auto border-2 rounded border-slate-300">
          {quiz == null ? (
            <StartQuizButton />
          ) : currentQuestionIndex < quiz.questions.length ? (
            <QuestionContainer />
          ) : (
            <QuizResult />
          )}
        </div>
      </QuizContext.Provider>
    </div>
  );
}

export default App;
