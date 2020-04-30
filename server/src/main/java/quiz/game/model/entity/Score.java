package quiz.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
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


}
