package quiz.game.model.dto;

import quiz.game.model.entity.Question;

import java.util.List;

public class QuestionDTO {
    private int id;
    private String questionName;
    private List<AnswerDTO> answers;
    private String progress;

    public QuestionDTO() {
    }

    public QuestionDTO(Question entity) {
        this.id = entity.getId();
        this.questionName = entity.getQuestionName();
    }

    public QuestionDTO(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
