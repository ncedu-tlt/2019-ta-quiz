package quiz.game.storage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
        List<Answer> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<Answer> getAllAnswersByQuestionId(int id) {
        Session session = sessionProvider.getSession();
        Criteria criteria = session.createCriteria(Answer.class);
        criteria.createAlias(DbConsts.Question.NAME, DbConsts.Question.NAME);
        criteria.add(Restrictions.eq(DbConsts.Question.NAME + "." + DbConsts.Question.Columns.ID, id));
        criteria.add(Restrictions.sqlRestriction("1=1 order by random()"));
        List<Answer> result = criteria.list();
        sessionProvider.closeSession();
        return result;
    }

    public Answer getAnswerById(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Answer> criteria = builder.createQuery(Answer.class);
        Root<Answer> rootCriteria = criteria.from(Answer.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Answer.Columns.ID), id));
        Answer result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }

    public List<Answer> addAnswer(Answer answer) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(answer);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return this.getAllAnswers();
    }
}
