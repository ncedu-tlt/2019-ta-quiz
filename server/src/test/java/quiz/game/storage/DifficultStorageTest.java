package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Difficult;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Transactional
class DifficultStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private DifficultStorage difficultStorage;

    @Test
    void getAllDifficult() {
        //given
        Difficult difficult = new Difficult("Easy", 1);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(difficult);

        //when
        List<Difficult> result = difficultStorage.getAllDifficult();
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("Easy", result.get(result.size()-1).getDifficultName());
    }

    @Test
    void getDifficultById() {
        //given
        Difficult difficult = new Difficult("Easy", 1);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(difficult);

        //when
        Difficult result = difficultStorage.getDifficultById(difficult.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("Easy", result.getDifficultName());
        assertEquals(difficult.getId(), result.getId());
        assertEquals(1, result.getDifficultFactor());
    }

    /*
    @Test
    void addDifficult() {
    }
*/
}