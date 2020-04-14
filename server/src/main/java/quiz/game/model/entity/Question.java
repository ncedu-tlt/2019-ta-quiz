package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = DbConsts.Question.NAME)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_Generator")
    @SequenceGenerator(name="sequence_Generator", sequenceName="QUESTION_ID_SEQ", initialValue = 41, allocationSize=1)
    @Column(name = DbConsts.Question.Columns.ID)
    private int id;

    @Column(name = DbConsts.Question.Columns.QUESTION_NAME)
    private String questionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Question.Columns.THEME_ID)
    private Theme theme;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Question.Columns.DIFFICULT_ID)
    private Difficult difficult;

    public Question() {
    }

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public Question(int id, String questionName, Theme theme, Difficult difficult) {
        this.id = id;
        this.questionName = questionName;
        this.theme = theme;
        this.difficult = difficult;
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

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Difficult getDifficult() {
        return difficult;
    }

    public void setDifficult(Difficult difficult) {
        this.difficult = difficult;
    }
}
