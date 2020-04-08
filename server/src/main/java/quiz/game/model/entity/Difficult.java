package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = DbConsts.Difficult.NAME)
public class Difficult {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = DbConsts.Difficult.Columns.ID)
    private int id;

    @Column(name = DbConsts.Difficult.Columns.DIFFICULT_NAME)
    private String difficultName;

    @Column(name = DbConsts.Difficult.Columns.DIFFICULT_FACTOR)
    private int difficultFactor;

    public Difficult() {
    }

    public Difficult(int id, String difficultName, int difficultFactor) {
        this.id = id;
        this.difficultName = difficultName;
        this.difficultFactor = difficultFactor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDifficultName() {
        return difficultName;
    }

    public void setDifficultName(String difficultName) {
        this.difficultName = difficultName;
    }

    public int getDifficultFactor() {
        return difficultFactor;
    }

    public void setDifficultFactor(int difficultFactor) {
        this.difficultFactor = difficultFactor;
    }

}
