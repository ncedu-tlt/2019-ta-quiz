package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.session.SessionProvider;
import quiz.game.model.entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class QuestionStorage {

    private final SessionProvider sessionProvider;

    public QuestionStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public List<Question> getAllQuestions() {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
        Root<Question> rootCriteria = criteria.from(Question.class);
        criteria.select(rootCriteria);
        List<Question> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public Question getQuestionById(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
        Root<Question> rootCriteria = criteria.from(Question.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Question.Columns.ID), id));
        Question result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }

    public List<Question> getQuestionByThemeAndDifId(int idTheme, int idDif) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
        Root<Question> rootCriteria = criteria.from(Question.class);
        criteria.select(rootCriteria).where(builder.and(
                builder.equal(rootCriteria.get(DbConsts.Theme.NAME).get(DbConsts.Theme.Columns.ID), idTheme),
                builder.equal(rootCriteria.get(DbConsts.Difficult.NAME).get(DbConsts.Difficult.Columns.ID), idDif)
        ));
        List<Question> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<Question> addQuestion(Question question) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(question);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return getAllQuestions();
    }
}
