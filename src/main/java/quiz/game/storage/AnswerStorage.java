package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Answer;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class AnswerStorage {
    private final SessionProvider sessionProvider;

    public AnswerStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public List<Answer> getAllAnswers() {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Answer> criteria = builder.createQuery(Answer.class);
        Root<Answer> rootCriteria = criteria.from(Answer.class);
        criteria.select(rootCriteria);
        return session.createQuery(criteria).getResultList();
    }

    public List<Answer> getAllAnswersByQuestionId(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Answer> criteria = builder.createQuery(Answer.class);
        Root<Answer> rootCriteria = criteria.from(Answer.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Question.NAME).get(DbConsts.Question.Columns.ID), id));
        return session.createQuery(criteria).getResultList();
    }

    public List<Answer> addAnswer(Answer answer) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(answer);
        session.getTransaction().commit();
        SessionProvider.closeSession();
        return this.getAllAnswers();
    }
}