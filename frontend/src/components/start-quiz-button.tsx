import axios from "axios";
import { useContext, useState, useCallback } from "react";
import { QuizContext, BASE_URL } from "../App";
import LoadingSpinner from "./loading-spinner";

export default function StartQuizButton() {
  const { setQuiz } = useContext(QuizContext);
  const [loading, setLoading] = useState<boolean>(false);

  const handleClick = useCallback(async () => {
    try {
      setLoading(true);
      const res = await axios.get(BASE_URL + "/start");
      const quiz = res.data as Quiz;

      setQuiz(quiz);
    } catch (ex) {
      console.log(ex);
      alert("Something went wrong, please try again later!");
    } finally {
      setLoading(false);
    }
  }, []);

  return (
    <div className="flex flex-col items-center justify-center h-full gap-3">
      <p>
        <u>Welcome!</u> click the button to start the quiz!
      </p>

      <button
        onClick={handleClick}
        className="px-4 py-2 bg-blue-500 hover:bg-blue-700 text-white font-medium rounded"
      >
        {loading ? (
          <>
            <LoadingSpinner />
            Starting...
          </>
        ) : (
          "Start Quiz!"
        )}
      </button>
    </div>
  );
}
