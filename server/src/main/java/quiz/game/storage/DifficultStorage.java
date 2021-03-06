package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Difficult;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class DifficultStorage {

    private final SessionProvider sessionProvider;

    public DifficultStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public List<Difficult> getAllDifficult() {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Difficult> criteria = builder.createQuery(Difficult.class);
        Root<Difficult> rootCriteria = criteria.from(Difficult.class);
        criteria.select(rootCriteria);
        List<Difficult> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<Difficult> addDifficult(Difficult difficult) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(difficult);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return getAllDifficult();
    }

    public Difficult getDifficultById(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Difficult> criteria = builder.createQuery(Difficult.class);
        Root<Difficult> rootCriteria = criteria.from(Difficult.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Difficult.Columns.ID), id));
        Difficult result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }
}
