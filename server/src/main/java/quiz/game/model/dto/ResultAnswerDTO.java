package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultAnswerDTO {
    private String answerText;
    private boolean answerIsCorrect;
    private boolean userAnswer;
}
