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
import static org.junit.jupiter.api.Assertions.*;

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
    }
}