package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.Score;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Component
public class ScoreStorage {

    private final SessionProvider sessionProvider;

    public ScoreStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public void addScore(Score score) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(score);
        session.getTransaction().commit();
        sessionProvider.closeSession();
    }

    public List<Score> getScoresByUserId(Long idUser) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Score> criteria = builder.createQuery(Score.class);
        Root<Score> rootCriteria = criteria.from(Score.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get("user"), idUser));
        List<Score> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }
}
