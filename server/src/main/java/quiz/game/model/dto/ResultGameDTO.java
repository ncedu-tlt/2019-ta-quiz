package quiz.game.model.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ResultGameDTO {
    private int Score;
    private List<ResultQuestionDTO> questions;

    public ResultGameDTO() {
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public List<ResultQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ResultQuestionDTO> questions) {
        this.questions = questions;
    }
}
