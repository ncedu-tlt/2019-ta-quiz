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

    public Score() {
    }

    public Score(UUID idGame, User user, int score, Date date) {
        this.idGame = idGame;
        this.user = user;
        this.score = score;
        this.date = date;
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
}
