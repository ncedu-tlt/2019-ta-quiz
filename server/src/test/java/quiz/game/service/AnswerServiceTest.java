package quiz.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.storage.AnswerStorage;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {
    @InjectMocks
    AnswerService answerService;

    @Mock
    AnswerStorage answerStorage;

    @Test
    void getAllAnswers() {
        //given
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        List<Answer> answers = Arrays.asList(new Answer( 1, "answer1", true, questionOne), new Answer(2, "answer2", false, questionOne));

        //when
        when(answerStorage.getAllAnswers()).thenReturn(answers);
        List<Answer> result = answerService.getAllAnswers();

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("answer1", result.get(0).getAnswerText());
        assertEquals(true, result.get(0).isAnswerIsCorrect());
        assertEquals("Who?", result.get(0).getQuestion().getQuestionName());
        assertEquals(2, result.get(1).getId());
        assertEquals("answer2", result.get(1).getAnswerText());
        assertEquals(false, result.get(1).isAnswerIsCorrect());
        assertEquals("Who?", result.get(1).getQuestion().getQuestionName());

        verify(answerStorage, times(1)).getAllAnswers();
    }

   @Test
    void getAnswerById() {
        //given
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        List<Answer> answers = Arrays.asList(new Answer( 1, "answer1", true, questionOne), new Answer(2, "answer2", false, questionOne));

        //when
        when(answerStorage.getAnswerById(1)).thenReturn(answers.get(0));
        Answer result = answerService.getAnswerById(1);

        //expect
        assertEquals(1, result.getId());
        assertEquals("answer1", result.getAnswerText());
        assertEquals(true, result.isAnswerIsCorrect());
        assertEquals("Who?", result.getQuestion().getQuestionName());
    }

    @Test
    void getAllAnswersByQuestionId() {
        //given
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        List<Answer> answers = Arrays.asList(new Answer( 1, "answer1", true, questionOne), new Answer(2, "answer2", false, questionOne));

        //when
        when(answerStorage.getAllAnswersByQuestionId(1)).thenReturn(answers);
        List<Answer> result = answerService.getAllAnswersByQuestionId(1);

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("answer1", result.get(0).getAnswerText());
        assertEquals(true, result.get(0).isAnswerIsCorrect());
        assertEquals("Who?", result.get(0).getQuestion().getQuestionName());
        assertEquals(2, result.get(1).getId());
        assertEquals("answer2", result.get(1).getAnswerText());
        assertEquals(false, result.get(1).isAnswerIsCorrect());
        assertEquals("Who?", result.get(1).getQuestion().getQuestionName());
    }

    @Test
    void getAllAnswersByQuestionIdWOCorrect() {
        //given
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        List<Answer> answers = Arrays.asList(new Answer( 1, "answer1", true, questionOne), new Answer(2, "answer2", false, questionOne));

        //when
        when(answerStorage.getAllAnswersByQuestionId(1)).thenReturn(answers);
        List<AnswerDTO> result = answerService.getAllAnswersByQuestionIdWOCorrect(1);

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("answer1", result.get(0).getAnswerText());
        assertEquals(2, result.get(1).getId());
        assertEquals("answer2", result.get(1).getAnswerText());
    }

/*
    @Test
    void addAnswer() {
    }

 */
}