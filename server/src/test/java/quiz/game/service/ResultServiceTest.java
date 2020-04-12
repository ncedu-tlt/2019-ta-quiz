package quiz.game.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.dto.ResultQuestionDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.User;
import quiz.game.storage.ResultStorage;

@SpringBootTest
public class ResultServiceTest {
    @InjectMocks
    ResultService resultService;

    @Mock
    ResultStorage resultStorage;

    @Mock
    AnswerService answerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getResultsByGameId() {
        //given
        UUID gameID = UUID.randomUUID();
        Date date = new Date();



        Question questionOne = new Question();
        Question questionTwo = new Question();

        questionOne.setId(1);
        questionOne.setQuestionName("Who?");
        questionTwo.setId(2);
        questionTwo.setQuestionName("What?");


        User user = new User();
        user.setId(1L);
        user.setUsername("user");
        user.setPassword("123");

        List<Answer> answerListOne = new ArrayList<>();
        List<Answer> answerListTwo = new ArrayList<>();

        Answer answerOne = new Answer();
        Answer answerTwo = new Answer();
        Answer answerThree = new Answer();
        Answer answerFour = new Answer();

        answerOne.setId(1);
        answerOne.setAnswerText("answer1");
        answerOne.setQuestion(questionOne);
        answerOne.setAnswerIsCorrect(true);
        answerTwo.setId(2);
        answerTwo.setAnswerText("answer2");
        answerTwo.setQuestion(questionOne);
        answerTwo.setAnswerIsCorrect(false);
        answerThree.setId(3);
        answerThree.setAnswerText("answer3");
        answerThree.setQuestion(questionTwo);
        answerThree.setAnswerIsCorrect(false);
        answerFour.setId(4);
        answerFour.setAnswerText("answer4");
        answerFour.setQuestion(questionTwo);
        answerFour.setAnswerIsCorrect(true);
        answerListOne.add(answerOne);
        answerListOne.add(answerTwo);
        answerListTwo.add(answerThree);
        answerListTwo.add(answerFour);

        List<Result> resultList = new ArrayList<>();
        Result resultOne = new Result();
        Result resultTwo = new Result();
        resultOne.setIdGame(gameID);
        resultOne.setUser(user);
        resultOne.setDate(date);
        resultOne.setAnswer(answerOne);
        resultOne.setId(UUID.randomUUID());
        resultTwo.setIdGame(gameID);
        resultTwo.setUser(user);
        resultTwo.setDate(date);
        resultTwo.setAnswer(answerThree);
        resultTwo.setId(UUID.randomUUID());
        resultList.add(resultOne);
        resultList.add(resultTwo);

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