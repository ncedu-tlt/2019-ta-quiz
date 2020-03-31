package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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

    public Result() {
    }

    public Result(Date date, UUID idGame, User user, Answer answer) {
        this.date = date;
        this.id_game = idGame;
        this.user = user;
        this.answer = answer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UUID getIdGame() {
        return id_game;
    }

    public void setIdGame(UUID idGame) {
        this.id_game = idGame;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}