package quiz.game.model.dto;

import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Result;

import java.util.ArrayList;
import java.util.List;

public class ResultDTO {

    private Answer userAnswer = new Answer();
    private QuestionDTO question = new QuestionDTO();
    private List<Answer> answers = new ArrayList<>();

    public ResultDTO() {
    }

    public ResultDTO(Result result, List<Answer> answerList) {
        this.userAnswer.setId(result.getAnswer().getId());
        this.userAnswer.setAnswerText(result.getAnswer().getAnswerText());
        this.userAnswer.setAnswerIsCorrect(result.getAnswer().isAnswerIsCorrect());
        this.question.setId(result.getAnswer().getQuestion().getId());
        this.question.setQuestionName(result.getAnswer().getQuestion().getQuestionName());
        for (Answer answer : answerList) {
            Answer temp = new Answer();
            temp.setId(answer.getId());
            temp.setAnswerText(answer.getAnswerText());
            temp.setAnswerIsCorrect(answer.isAnswerIsCorrect());
            this.answers.add(temp);
        }
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }

    public QuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionDTO question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
