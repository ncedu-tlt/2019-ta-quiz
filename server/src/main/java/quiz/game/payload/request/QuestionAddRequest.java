package quiz.game.payload.request;

import lombok.Data;
import quiz.game.model.entity.Answer;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class QuestionAddRequest {

    @NotBlank
    private String questionName;

    @NotBlank
    private int themeId;

    @NotBlank
    private int difficultId;

    @NotBlank
    private List<Answer> answers;
}
