package quiz.game.model.dto;

import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultQuestionDTO {

    private String questionName;
    private List<ResultAnswerDTO> answers;

    public ResultQuestionDTO() {
    }

    public ResultQuestionDTO(String questionName, List<ResultAnswerDTO> answers) {
        this.questionName = questionName;
        this.answers = answers;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<ResultAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ResultAnswerDTO> answers) {
        this.answers = answers;
    }
}
