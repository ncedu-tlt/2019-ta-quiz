package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.*;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Transactional
class ResultStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private ResultStorage resultStorage;

    @Test
    void getResultsByUserId() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Answer answer = new Answer( 1, "answer1", true, question);
        User user = new User(1L, "user","123");

        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        Result res = new Result(date, gameID, user, answer);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);
        session.save(answer);
        session.save(user);
        session.save(res);

        //when
        List<Result> result = resultStorage.getResultsByUserId(user.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("answer1", result.get(result.size()-1).getAnswer().getAnswerText());
        assertEquals("Who?", result.get(result.size()-1).getAnswer().getQuestion().getQuestionName());
        assertEquals("Easy", result.get(result.size()-1).getAnswer().getQuestion().getDifficult().getDifficultName());
        assertEquals("History", result.get(result.size()-1).getAnswer().getQuestion().getTheme().getThemeName());
    }

    @Test
    void getResultsByGameId() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Answer answer = new Answer( 1, "answer1", true, question);
        User user = new User(1L, "user","123");

        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        Result res = new Result(date, gameID, user, answer);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);
        session.save(answer);
        session.save(user);
        session.save(res);

        //when
        List<Result> result = resultStorage.getResultsByGameId(gameID);
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals(gameID, result.get(result.size()-1).getId_game());
        assertEquals("answer1", result.get(result.size()-1).getAnswer().getAnswerText());
        assertEquals("Who?", result.get(result.size()-1).getAnswer().getQuestion().getQuestionName());
        assertEquals("Easy", result.get(result.size()-1).getAnswer().getQuestion().getDifficult().getDifficultName());
        assertEquals("History", result.get(result.size()-1).getAnswer().getQuestion().getTheme().getThemeName());
    }
}