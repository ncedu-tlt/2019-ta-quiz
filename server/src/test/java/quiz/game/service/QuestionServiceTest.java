package quiz.game.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.storage.AnswerStorage;
import quiz.game.storage.QuestionStorage;

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
        List<Question> questions = new ArrayList<>();
        Question questionOne = new Question();
        Question questionTwo = new Question();

        questionOne.setId(1);
        questionOne.setQuestionName("Who?");
        questionTwo.setId(2);
        questionTwo.setQuestionName("What?");

        Theme themeOne = new Theme();
        Theme themeTwo = new Theme();

        themeOne.setId(1);
        themeOne.setThemeName("History");
        themeTwo.setId(2);
        themeTwo.setThemeName("Geography");

        Difficult difOne = new Difficult();
        Difficult difTwo = new Difficult();

        difOne.setId(1);
        difOne.setDifficultName("Easy");
        difOne.setDifficultFactor(1);
        difTwo.setId(2);
        difTwo.setDifficultName("Normal");
        difTwo.setDifficultFactor(2);

        questionOne.setTheme(themeOne);
        questionOne.setDifficult(difOne);
        questionTwo.setTheme(themeTwo);
        questionTwo.setDifficult(difTwo);

        questions.add(questionOne);
        questions.add(questionTwo);

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
        Question questionOne = new Question();

        questionOne.setId(1);
        questionOne.setQuestionName("Who?");

        Theme themeOne = new Theme();

        themeOne.setId(1);
        themeOne.setThemeName("History");

        Difficult difOne = new Difficult();

        difOne.setId(1);
        difOne.setDifficultName("Easy");
        difOne.setDifficultFactor(1);

        questionOne.setTheme(themeOne);
        questionOne.setDifficult(difOne);

        List<AnswerDTO> answers = new ArrayList<>();
        AnswerDTO answerOne = new AnswerDTO();
        AnswerDTO answerTwo = new AnswerDTO();

        answerOne.setId(1);
        answerOne.setAnswerText("answer1");
        answerTwo.setId(2);
        answerTwo.setAnswerText("answer2");
        answers.add(answerOne);
        answers.add(answerTwo);

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
        List<Question> questions = new ArrayList<>();
        Question questionOne = new Question();

        questionOne.setId(1);
        questionOne.setQuestionName("Who?");

        Theme themeOne = new Theme();
        themeOne.setId(1);
        themeOne.setThemeName("History");

        Difficult difOne = new Difficult();
        difOne.setId(1);
        difOne.setDifficultName("Easy");
        difOne.setDifficultFactor(1);

        questionOne.setTheme(themeOne);
        questionOne.setDifficult(difOne);

        questions.add(questionOne);

        List<AnswerDTO> answers = new ArrayList<>();
        AnswerDTO answerOne = new AnswerDTO();
        AnswerDTO answerTwo = new AnswerDTO();

        answerOne.setId(1);
        answerOne.setAnswerText("answer1");
        answerTwo.setId(2);
        answerTwo.setAnswerText("answer2");
        answers.add(answerOne);
        answers.add(answerTwo);

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