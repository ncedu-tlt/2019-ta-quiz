package quiz.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import quiz.game.model.entity.Question;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private int id;
    private String questionName;
    private List<AnswerDTO> answers;
    private String progress;

    public QuestionDTO(Question entity) {
        this.id = entity.getId();
        this.questionName = entity.getQuestionName();
    }

    public QuestionDTO(int id, String questionName) {
        this.id = id;
        this.questionName = questionName;
    }

    public QuestionDTO(int id, String questionName, List<AnswerDTO> answers) {
        this.id = id;
        this.questionName = questionName;
        this.answers = answers;
    }
}
