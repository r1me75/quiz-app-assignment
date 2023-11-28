type Question = {
  type: "boolean" | "multiple";
  difficulty: "easy" | "medium" | "hard";
  category: string;
  question: string;
  answers: string[];
};

type Quiz = {
  id: string;
  questions: Question[];
};

type CheckAnswerReponse = {
  question: string;
  answer: string;
  correctAnswer: string;
  correct: boolean;
};

type QuizContextProps = {
  quiz: Quiz | null;
  currentQuestionIndex: number;
  currentQuestion: Question | null;
  showNextQuestion: boolean;
  correctAnswers: number;

  setQuiz: (quiz: Quiz | null) => void;
  setCurrentQuestionIndex: (index: number) => void;
  setShowNextQuestion: (show: boolean) => void;
  setCorrectAnswers: (correctAnswers: number) => void;
  resetQuiz: () => void;
};
