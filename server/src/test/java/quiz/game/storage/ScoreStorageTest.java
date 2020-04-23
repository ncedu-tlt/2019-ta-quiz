package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Score;
import quiz.game.model.entity.Theme;
import quiz.game.model.entity.User;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Transactional
class ScoreStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private ScoreStorage scoreStorage;

    @Test
    void getScoresByUserId() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");

        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        User user = new User(1L, "user","123");
        Score score = new Score(gameID, user, 100, date, theme, difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(user);
        session.save(score);

        //when
        List<Score> result = scoreStorage.getScoresByUserId(user.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals(100, result.get(result.size()-1).getScore());
        assertEquals(gameID, result.get(result.size()-1).getIdGame());
        assertEquals(date, result.get(result.size()-1).getDate());
        assertEquals("user", result.get(result.size()-1).getUser().getUsername());
        assertEquals("Easy", result.get(result.size()-1).getDifficult().getDifficultName());
        assertEquals("History", result.get(result.size()-1).getTheme().getThemeName());
    }

    @Test
    void getUserGamesCount_User() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");

        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        User user = new User(1L, "user","123");
        Score score = new Score(gameID, user, 100, date, theme, difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(user);
        session.save(score);

        //when
        Long result = scoreStorage.getUserGamesCount(user.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals(1L, (long)result);
    }

    @Test
    void testGetUserGamesCount_UserThemeDif() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");

        UUID gameID = UUID.randomUUID();
        Date date = new Date();

        User user = new User(1L, "user","123");
        Score score = new Score(gameID, user, 100, date, theme, difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(user);
        session.save(score);

        //when
        Long result = scoreStorage.getUserGamesCount(user.getId(), theme.getId(), difficult.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals(1L, (long)result);
    }

    @Test
    void getUserSumScore() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");

        UUID gameID = UUID.randomUUID();
        UUID gameID2 = UUID.randomUUID();
        Date date = new Date();

        User user = new User(1L, "user","123");
        Score score = new Score(gameID, user, 100, date, theme, difficult);
        Score score2 = new Score(gameID2, user, 100, date, theme, difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(user);
        session.save(score);
        session.save(score2);
        //when
        Integer result = scoreStorage.getUserSumScore(user.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals(200, (int)result);
    }

    @Test
    void testGetUserSumScore() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Theme theme2 = new Theme(2, "Geography");

        UUID gameID = UUID.randomUUID();
        UUID gameID2 = UUID.randomUUID();
        Date date = new Date();

        User user = new User(1L, "user","123");
        Score score = new Score(gameID, user, 100, date, theme, difficult);
        Score score2 = new Score(gameID2, user, 100, date, theme2, difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(theme2);
        session.save(user);
        session.save(score);
        session.save(score2);
        //when
        Integer result = scoreStorage.getUserSumScore(user.getId(), theme.getId(), difficult.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals(100, (int)result);
    }
}