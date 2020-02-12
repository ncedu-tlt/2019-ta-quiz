package quiz.game.model;

import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
@Table(name = DbConsts.Answer.NAME)
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = DbConsts.Answer.Columns.ID)
    private int id;

    @Column(name = DbConsts.Answer.Columns.ANSWER_TEXT)
    private String answerText;

    @Column(name = DbConsts.Answer.Columns.ANSWER_IS_CORRECT)
    private boolean answerIsCorrect;

    @ManyToOne
    @JoinColumn(name = DbConsts.Answer.Columns.QUESTION_ID)
    private Question question;

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

    public boolean isAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public void setAnswerIsCorrect(boolean answerIsCorrect) {
        this.answerIsCorrect = answerIsCorrect;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
