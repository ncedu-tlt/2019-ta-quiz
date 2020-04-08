package quiz.game.model.dto;

public class ResultAnswerDTO {
    private String answerText;
    private boolean answerIsCorrect;
    private boolean userAnswer;

    public ResultAnswerDTO() {
    }

    public ResultAnswerDTO(String answerText, boolean answerIsCorrect, boolean userAnswer) {
        this.answerText = answerText;
        this.answerIsCorrect = answerIsCorrect;
        this.userAnswer = userAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public void setAnswerIsCorrect(boolean answerIsCorrect) {
        this.answerIsCorrect = answerIsCorrect;
    }

    public boolean isUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(boolean userAnswer) {
        this.userAnswer = userAnswer;
    }
}
