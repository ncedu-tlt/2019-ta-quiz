package quiz.game.model.entity;

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

/*
    @ManyToOne
    @JoinColumn(name = DbConsts.Answer.Columns.QUESTION_ID)
    private Question question;
 */
    @Column(name = DbConsts.Answer.Columns.QUESTION_ID)
    private int id_question;

    public Answer() {
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

    public boolean isAnswerIsCorrect() {
        return answerIsCorrect;
    }

    public void setAnswerIsCorrect(boolean answerIsCorrect) {
        this.answerIsCorrect = answerIsCorrect;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }
}
