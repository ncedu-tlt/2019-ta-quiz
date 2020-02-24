package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
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
        return session.createQuery(criteria).getResultList();
    }

    public List<Difficult> addDifficult(Difficult difficult) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(difficult);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return getAllDifficult();
    }
}
