package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = DbConsts.Result.NAME)
public class Result {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = DbConsts.Result.Columns.ID)
    private UUID id;

    @Column(name = DbConsts.Result.Columns.DATE)
    private Date date;

    @Column(name = DbConsts.Result.Columns.GAME_ID)
    private UUID id_game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Result.Columns.USER_ID)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Result.Columns.ANSWER_ID)
    private Answer answer;

    public Result(Date date, UUID idGame, User user, Answer answer) {
        this.date = date;
        this.id_game = idGame;
        this.user = user;
        this.answer = answer;
    }
}
