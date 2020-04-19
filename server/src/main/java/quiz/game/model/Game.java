package quiz.game.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Game {
    private UUID gameId;
    private int chosenThemeId;
    private int chosenDifId;
    private List<QuestionDTO> questionList;
    private List<Result> userAnswers = new ArrayList<>();
    private int progress;
    private int score;

    public Game(int chosenThemeId, int chosenDifId, List<QuestionDTO> questionList) {
        this.chosenThemeId = chosenThemeId;
        this.chosenDifId = chosenDifId;
        this.questionList = questionList;
        this.gameId = UUID.randomUUID();
        this.progress = 0;
    }

    public QuestionDTO getNextQuestion() {
        if (this.getProgress() != this.questionList.size()) {
            return nextQuestion();
        } else {
            return endGame();
        }
    }

    private QuestionDTO nextQuestion() {
        QuestionDTO question = this.questionList.get(this.progress);
        question.setProgress(progress+1 + "/" + this.questionList.size());
        this.progress++;
        return question;
    }

    private QuestionDTO endGame() {
        QuestionDTO question = new QuestionDTO();
        question.setId(-1);
        return question;
    }

    public void addUserAnswer(Result userAnswer) {
        this.userAnswers.add(userAnswer);
    }
}
