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
@Table(name = DbConsts.Question.NAME)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_Generator")
    @SequenceGenerator(name="sequence_Generator", sequenceName="QUESTION_ID_SEQ", initialValue = 41, allocationSize=1)
    @Column(name = DbConsts.Question.Columns.ID)
    private int id;

    @Column(name = DbConsts.Question.Columns.QUESTION_NAME)
    private String questionName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Question.Columns.THEME_ID)
    private Theme theme;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = DbConsts.Question.Columns.DIFFICULT_ID)
    private Difficult difficult;

    public Question(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }
}
