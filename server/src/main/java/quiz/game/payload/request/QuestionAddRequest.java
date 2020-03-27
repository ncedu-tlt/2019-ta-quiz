package quiz.game.payload.request;

import quiz.game.model.entity.Answer;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class QuestionAddRequest {

    @NotBlank
    private String questionName;

    @NotBlank
    private int themeId;

    @NotBlank
    private int difficultId;

    @NotBlank
    private List<Answer> answers;

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getDifficultId() {
        return difficultId;
    }

    public void setDifficultId(int difficultId) {
        this.difficultId = difficultId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
