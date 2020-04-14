package quiz.game.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.storage.QuestionStorage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class QuestionServiceTest {
    @InjectMocks
    QuestionService questionService;

    @Mock
    QuestionStorage questionStorage;
    @Mock
    AnswerService answerService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllQuestions() {
        //given
        List<Question> questions = Arrays.asList(
                new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1)),
                new Question(2, "What?", new Theme(2, "Geography"), new Difficult(2, "Normal", 2))
        );

        //when
        when(questionStorage.getAllQuestions()).thenReturn(questions);
        List<Question> result = questionService.getAllQuestions();

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Who?", result.get(0).getQuestionName());
        assertEquals(2, result.get(1).getId());
        assertEquals("What?", result.get(1).getQuestionName());

        assertEquals(1, result.get(0).getTheme().getId());
        assertEquals("History", result.get(0).getTheme().getThemeName());
        assertEquals(2, result.get(1).getTheme().getId());
        assertEquals("Geography", result.get(1).getTheme().getThemeName());

        assertEquals(1, result.get(0).getDifficult().getId());
        assertEquals("Easy", result.get(0).getDifficult().getDifficultName());
        assertEquals(1, result.get(0).getDifficult().getDifficultFactor());
        assertEquals(2, result.get(1).getDifficult().getId());
        assertEquals("Normal", result.get(1).getDifficult().getDifficultName());
        assertEquals(2, result.get(1).getDifficult().getDifficultFactor());

        verify(questionStorage, times(1)).getAllQuestions();
    }

    @Test
    public void getQuestionById() {
        //given
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        List<AnswerDTO> answers = Arrays.asList(new AnswerDTO( 1, "answer1"), new AnswerDTO(2, "answer2"));

        //when
        when(questionStorage.getQuestionById(1)).thenReturn(questionOne);
        when(answerService.getAllAnswersByQuestionIdWOCorrect(1)).thenReturn(answers);
        QuestionDTO result = questionService.getQuestionById(1);

        //expect
        assertEquals(1, result.getId());
        assertEquals("Who?", result.getQuestionName());
        assertEquals(1, result.getAnswers().get(0).getId());
        assertEquals("answer1", result.getAnswers().get(0).getAnswerText());
        assertEquals(2, result.getAnswers().get(1).getId());
        assertEquals("answer2", result.getAnswers().get(1).getAnswerText());
    }

    @Test
    public void getQuestionsByThemeAndDifId() {
        //given
        List<Question> questions = Arrays.asList(
                new Question(1, "Who?",new Theme(1,"History"), new Difficult(1, "Easy", 1))
        );
        List<AnswerDTO> answers = Arrays.asList(new AnswerDTO(1,"answer1"), new AnswerDTO(2,"answer2"));

        //when
        when(questionStorage.getQuestionsByThemeAndDifId(1,1,1)).thenReturn(questions);
        when(answerService.getAllAnswersByQuestionIdWOCorrect(1)).thenReturn(answers);
        List<QuestionDTO> result = questionService.getQuestionsByThemeAndDifId(1,1,1);

        //expect
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("Who?", result.get(0).getQuestionName());
        assertEquals(1, result.get(0).getAnswers().get(0).getId());
        assertEquals("answer1", result.get(0).getAnswers().get(0).getAnswerText());
        assertEquals(2, result.get(0).getAnswers().get(1).getId());
        assertEquals("answer2", result.get(0).getAnswers().get(1).getAnswerText());

    }
/*
    @Test
    public void addQuestion() {
    }

 */
}