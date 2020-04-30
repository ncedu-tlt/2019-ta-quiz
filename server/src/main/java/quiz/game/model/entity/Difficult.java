package quiz.game.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.DbConsts;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Difficult(String difficultName, int difficultFactor) {
        this.difficultName = difficultName;
        this.difficultFactor = difficultFactor;
    }
}
