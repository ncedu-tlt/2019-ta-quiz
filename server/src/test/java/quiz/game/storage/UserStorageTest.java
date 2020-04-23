package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.User;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class UserStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private UserStorage userStorage;

    @Test
    void findByUsername() {
        //given
        User user = new User(1L, "user","123");
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(user);

        //when
        User result = userStorage.findByUsername("user");
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("user", result.getUsername());
    }

    @Test
    void existsByUsername() {
        //given
        User user = new User(1L, "user","123");
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(user);

        //when
        Boolean result = userStorage.existsByUsername("user");
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertTrue(result);
    }

    @Test
    void existsByUsername_Negative() {
        //given
        User user = new User(1L, "user","123");
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(user);

        //when
        Boolean result = userStorage.existsByUsername("admin");
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result);
    }
}