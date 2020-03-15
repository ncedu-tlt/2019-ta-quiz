package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = DbConsts.Result.NAME)
public class Result {
    @Id
    @Column(name = DbConsts.Result.Columns.DATE)
    private Date date;

    @Column(name = DbConsts.Result.Columns.SESSION_ID)
    private String idSession;

    @Column(name = DbConsts.Result.Columns.USER_ID)
    private int idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = DbConsts.Result.Columns.ANSWER_ID)
    private Answer answer;

    public Result() {
    }

    public Result(Date date, String idSession, int idUser, Answer answer) {
        this.date = date;
        this.idSession = idSession;
        this.idUser = idUser;
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
