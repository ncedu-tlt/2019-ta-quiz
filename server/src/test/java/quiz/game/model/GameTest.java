package quiz.game.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Result;
import quiz.game.service.GameService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class GameTest {

    @Test
    void getNextQuestion_One() {
        //given
        List<AnswerDTO> answers = Arrays.asList(new AnswerDTO( 1, "answer1"), new AnswerDTO(2, "answer2"));
        List<QuestionDTO> questions = Arrays.asList(new QuestionDTO(1, "Who?", answers));
        Game game = new Game(1,1, questions);

        //when
        QuestionDTO result = game.getNextQuestion();

        //expect
        assertEquals(1, result.getId());
        assertEquals("Who?", result.getQuestionName());
        assertEquals(1, result.getAnswers().get(0).getId());
        assertEquals("answer1", result.getAnswers().get(0).getAnswerText());
        assertEquals(2, result.getAnswers().get(1).getId());
        assertEquals("answer2", result.getAnswers().get(1).getAnswerText());
        assertEquals("1/1", result.getProgress());
    }

    @Test
    void getNextQuestion_Null() {
        //given
        Game game = new Game(1,1, new ArrayList<>());

        //when
        QuestionDTO result = game.getNextQuestion();

        //expect
        assertEquals(-1, result.getId());
        assertEquals(null, result.getQuestionName());
        assertEquals(null, result.getAnswers());
        assertEquals(null, result.getProgress());
    }

    @Test
    void addUserAnswer() {
        //given
        List<QuestionDTO> questions = new ArrayList<>();
        Game game = new Game(1,1, questions);
        Result userAnswer = new Result();

        //when
        game.addUserAnswer(userAnswer);

        //expect
        assertEquals(userAnswer, game.getUserAnswers().get(0));
    }
}