package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Result;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultQuestionDTO {
    private String questionName;
    private List<ResultAnswerDTO> answers;
}
