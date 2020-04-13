package quiz.game.model.entity;

import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = DbConsts.Score.NAME)
public class Score {

    @Id
    @Column(name = DbConsts.Score.Columns.GAME_ID)
    private UUID idGame;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Score.Columns.USER_ID)
    private User user;

    @Column(name = DbConsts.Score.Columns.SCORE)
    private int score;

    @Column(name = DbConsts.Score.Columns.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Score.Columns.THEME_ID)
    private Theme theme;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Score.Columns.DIFFICULT_ID)
    private Difficult difficult;

    public Score() {
    }

    public Score(UUID idGame, User user, int score, Date date, Theme theme, Difficult difficult) {
        this.idGame = idGame;
        this.user = user;
        this.score = score;
        this.date = date;
        this.theme = theme;
        this.difficult = difficult;
    }

    public UUID getIdGame() {
        return idGame;
    }

    public void setIdGame(UUID idGame) {
        this.idGame = idGame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
