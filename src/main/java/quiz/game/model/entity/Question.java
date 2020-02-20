package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = DbConsts.Question.NAME)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = DbConsts.Question.Columns.ID)
    private int id;

    @Column(name = DbConsts.Question.Columns.QUESTION_NAME)
    private String questionName;
/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DbConsts.Question.Columns.THEME_ID)
    private Theme theme;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DbConsts.Question.Columns.DIFFICULT_ID)
    private Difficult difficult;
*/
    @Column(name = DbConsts.Question.Columns.THEME_ID)
    private int theme_id;

    @Column(name = DbConsts.Question.Columns.DIFFICULT_ID)
    private int difficult_id;

    public Question() {
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

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public int getDifficult_id() {
        return difficult_id;
    }

    public void setDifficult_id(int difficult_id) {
        this.difficult_id = difficult_id;
    }
}
