package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.entity.Answer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {
    private int id;
    private String answerText;

    public AnswerDTO(Answer entity) {
        this.id = entity.getId();
        this.answerText = entity.getAnswerText();
    }
}
