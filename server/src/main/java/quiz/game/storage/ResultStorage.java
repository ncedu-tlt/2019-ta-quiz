package quiz.game.storage;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Result;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

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

    public List<Result> getResultsByUserId(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Result> criteria = builder.createQuery(Result.class);
        Root<Result> rootCriteria = criteria.from(Result.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.User.NAME).get(DbConsts.User.Columns.ID), id));
        List<Result> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<Result> getResultsByGameId(UUID id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Result> criteria = builder.createQuery(Result.class);
        Root<Result> rootCriteria = criteria.from(Result.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Result.Columns.GAME_ID), id));
        List<Result> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<UUID> getUserGames(Long idUser) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UUID> criteria = builder.createQuery(UUID.class);
        Root<Result> rootCriteria = criteria.from(Result.class);
        criteria.select(rootCriteria.get(DbConsts.Result.Columns.GAME_ID)).where(builder.equal(rootCriteria.get("user"), idUser)).distinct(true);
        List<UUID> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }
}
