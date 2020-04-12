package quiz.game.model.dto;

import quiz.game.model.entity.Answer;

public class AnswerDTO {
    private int id;
    private String answerText;

    public AnswerDTO() {
    }

    public AnswerDTO(Answer entity) {
        this.id = entity.getId();
        this.answerText = entity.getAnswerText();
    }

    public AnswerDTO(int id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

}
