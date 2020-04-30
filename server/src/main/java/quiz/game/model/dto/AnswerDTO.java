package quiz.game.model.dto;

import lombok.*;
import quiz.game.model.entity.Answer;

@Getter
@Setter
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
