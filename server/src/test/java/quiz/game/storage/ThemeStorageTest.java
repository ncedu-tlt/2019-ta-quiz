package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Theme;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Transactional
class ThemeStorageTest {
    @Autowired private SessionProvider sessionProvider;

    @Autowired
    private ThemeStorage themeStorage;

    @Test
    void getAllThemes() {
        //given
        Theme theme = new Theme("History");
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(theme);

        //when
        List<Theme> result = themeStorage.getAllThemes();
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("History", result.get(result.size()-1).getThemeName());
    }

    @Test
    void getThemeById() {
        //given
        Theme theme = new Theme("History");
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(theme);

        //when
        Theme result = themeStorage.getThemeById(theme.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("History", result.getThemeName());
        assertEquals(theme.getId(), result.getId());
    }
/*
    @Test
    void addTheme() {

    }
 */

}