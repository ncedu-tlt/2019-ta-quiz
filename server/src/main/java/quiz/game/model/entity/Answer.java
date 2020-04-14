package quiz.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.DbConsts;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DbConsts.Answer.NAME)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sequence_Generator")
    @SequenceGenerator(name="sequence_Generator", sequenceName="ANSWER_ID_SEQ", initialValue = 161, allocationSize=1)
    @Column(name = DbConsts.Answer.Columns.ID)
    private int id;

    @Column(name = DbConsts.Answer.Columns.ANSWER_TEXT)
    private String answerText;

    @Column(name = DbConsts.Answer.Columns.ANSWER_IS_CORRECT)
    private boolean answerIsCorrect;

    @ManyToOne
    @JoinColumn(name = DbConsts.Answer.Columns.QUESTION_ID)
    private Question question;
}
