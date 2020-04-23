package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private AnswerStorage answerStorage;

    @Test
    void getAllAnswers() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Answer answer = new Answer( 1, "answer1", true, question);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);
        session.save(answer);

        //when
        List<Answer> result = answerStorage.getAllAnswers();
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("answer1", result.get(result.size()-1).getAnswerText());
        assertEquals("Who?", result.get(result.size()-1).getQuestion().getQuestionName());
        assertEquals("History", result.get(result.size()-1).getQuestion().getTheme().getThemeName());
        assertEquals("Easy", result.get(result.size()-1).getQuestion().getDifficult().getDifficultName());
    }

    @Test
    void getAnswerById() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Answer answer = new Answer( 1, "answer1", true, question);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);
        session.save(answer);

        //when
        Answer result = answerStorage.getAnswerById(answer.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("answer1", result.getAnswerText());
        assertEquals(answer.getId(), result.getId());
    }


    @Test
    void getAllAnswersByQuestionId() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1000, "Who?", theme , difficult);
        Answer answer = new Answer( 1, "answer1", true, question);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);
        session.save(answer);

        //when
        List<Answer> result = answerStorage.getAllAnswersByQuestionId(answer.getQuestion().getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("answer1", result.get(0).getAnswerText());
        assertEquals(answer.getId(), result.get(0).getId());
    }


/*
    @Test
    void addAnswer() {
    }
     */
}