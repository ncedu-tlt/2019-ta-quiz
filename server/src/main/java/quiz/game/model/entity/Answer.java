package quiz.game.model.entity;

import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
@Table(name = DbConsts.Answer.NAME)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_Generator")
    @SequenceGenerator(name="sequence_Generator", sequenceName="ANSWER_ID_SEQ", initialValue = 161, allocationSize=1)
    @Column(name = DbConsts.Answer.Columns.ID)
    private int id;

    @Column(name = DbConsts.Answer.Columns.ANSWER_TEXT)
    private String answerText;

    @Column(name = DbConsts.Answer.Columns.ANSWER_IS_CORRECT)
    private boolean answerIsCorrect;

    @ManyToOne
    @JoinColumn(name = DbConsts.Answer.Columns.QUESTION_ID)
    private Question question;

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

    public boolean getAnswerIsCorrect() {
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
