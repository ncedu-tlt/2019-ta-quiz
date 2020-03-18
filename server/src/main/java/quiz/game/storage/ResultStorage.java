package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Result;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class ResultStorage {

    private final SessionProvider sessionProvider;

    public ResultStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public void addUserAnswer(Result result) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(result);
        session.getTransaction().commit();
        sessionProvider.closeSession();
    }

    public Result getResultsByUserId(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Result> criteria = builder.createQuery(Result.class);
        Root<Result> rootCriteria = criteria.from(Result.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.User.NAME).get(DbConsts.User.Columns.ID), id));
        Result result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }

}
