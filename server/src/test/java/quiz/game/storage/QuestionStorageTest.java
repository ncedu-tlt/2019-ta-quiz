package quiz.game.storage;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.session.SessionProvider;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@Transactional
class QuestionStorageTest {
    @Autowired
    private SessionProvider sessionProvider;

    @Autowired
    private QuestionStorage questionStorage;

    @Test
    void getAllQuestions() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1000, "Who?", theme , difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);

        //when
        List<Question> result = questionStorage.getAllQuestions();
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("Who?", result.get(result.size()-1).getQuestionName());
        assertEquals("Easy", result.get(result.size()-1).getDifficult().getDifficultName());
        assertEquals("History", result.get(result.size()-1).getTheme().getThemeName());
    }

    @Test
    void getQuestionById() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);

        //when
        Question result = questionStorage.getQuestionById(question.getId());
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertEquals("Who?", result.getQuestionName());
        assertEquals("Easy", result.getDifficult().getDifficultName());
        assertEquals("History", result.getTheme().getThemeName());
    }

    @Test
    void getQuestionsByThemeAndDifId() {
        //given
        Difficult difficult = new Difficult(1, "Easy", 1);
        Theme theme = new Theme(1, "History");
        Question question = new Question(1, "Who?", theme , difficult);
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(difficult);
        session.save(theme);
        session.save(question);

        //when
        List<Question> result = questionStorage.getQuestionsByThemeAndDifId(theme.getId(), difficult.getId(), 1);
        session.getTransaction().rollback();
        sessionProvider.closeSession();

        //expect
        assertFalse(result.isEmpty());
        assertEquals("Who?", result.get(result.size()-1).getQuestionName());
        assertEquals("Easy", result.get(result.size()-1).getDifficult().getDifficultName());
        assertEquals("History", result.get(result.size()-1).getTheme().getThemeName());
    }
}