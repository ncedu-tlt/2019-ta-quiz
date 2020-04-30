package quiz.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import quiz.game.model.dto.ResultQuestionDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.User;
import quiz.game.storage.ResultStorage;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ResultServiceTest {
    @InjectMocks
    ResultService resultService;

    @Mock
    ResultStorage resultStorage;

    @Mock
    AnswerService answerService;

    @Test
    void getResultsByGameId() {
        //given
        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        Question questionOne = new Question(1, "Who?");
        Question questionTwo = new Question(2, "What?");

        User user = new User(1L, "user","123");

        Answer answerOne = new Answer(1,"answer1", true, questionOne);
        Answer answerTwo = new Answer(2, "answer2", false, questionOne);
        Answer answerThree = new Answer(3,"answer3",false, questionTwo);
        Answer answerFour = new Answer(4, "answer4", true, questionTwo);

        List<Answer> answerListOne = Arrays.asList(answerOne, answerTwo);
        List<Answer> answerListTwo = Arrays.asList(answerThree, answerFour);

        List<Result> resultList = Arrays.asList(
                new Result(UUID.randomUUID(), date, gameID, user, answerOne),
                new Result(UUID.randomUUID(), date, gameID, user, answerThree)
        );

        //when
        when(resultStorage.getResultsByGameId(gameID)).thenReturn(resultList);
        when(answerService.getAllAnswersByQuestionId(questionOne.getId())).thenReturn(answerListOne);
        when(answerService.getAllAnswersByQuestionId(questionTwo.getId())).thenReturn(answerListTwo);
        List<ResultQuestionDTO> result = resultService.getResultsByGameId(gameID);

        //expect
        assertEquals(2, result.size());
        assertEquals("Who?", result.get(0).getQuestionName());
        assertEquals("answer1", result.get(0).getAnswers().get(0).getAnswerText());
        assertEquals(true, result.get(0).getAnswers().get(0).isAnswerIsCorrect());
        assertEquals(true, result.get(0).getAnswers().get(0).isUserAnswer());
        assertEquals("answer2", result.get(0).getAnswers().get(1).getAnswerText());
        assertEquals(false, result.get(0).getAnswers().get(1).isAnswerIsCorrect());
        assertEquals(false, result.get(0).getAnswers().get(1).isUserAnswer());

        assertEquals("What?", result.get(1).getQuestionName());
        assertEquals("answer3", result.get(1).getAnswers().get(0).getAnswerText());
        assertEquals(false, result.get(1).getAnswers().get(0).isAnswerIsCorrect());
        assertEquals(true, result.get(1).getAnswers().get(0).isUserAnswer());
        assertEquals("answer4", result.get(1).getAnswers().get(1).getAnswerText());
        assertEquals(true, result.get(1).getAnswers().get(1).isAnswerIsCorrect());
        assertEquals(false, result.get(1).getAnswers().get(1).isUserAnswer());
    }
}