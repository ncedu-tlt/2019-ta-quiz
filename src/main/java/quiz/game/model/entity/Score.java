package quiz.game.model.entity;

import quiz.game.DbConsts;

import javax.persistence.*;

@Entity
@Table(name = DbConsts.Score.NAME)
public class Score {

    @Id
    @GeneratedValue
    @Column(name = DbConsts.Score.Columns.ID)
    private int id;

    @Column(name = DbConsts.Score.Columns.SCORE_NAME_PLAYER)
    private String PlayerName;

    @Column(name = DbConsts.Score.Columns.SCORE_RESULT)
    private double scoreResult;

    public Score() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public double getScoreResult() {
        return scoreResult;
    }

    public void setScoreResult(double scoreResult) {
        this.scoreResult = scoreResult;
    }
}
