package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.User;
import quiz.game.session.SessionProvider;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
public class UserStorage {

    private final SessionProvider sessionProvider;

    public UserStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public User findByUsername(String username) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> rootCriteria = criteria.from(User.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.User.Columns.USERNAME),username));
        User result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }

    public void addUser(User user) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        sessionProvider.closeSession();
    }

    public Boolean existsByUsername(String username) {
        try {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> rootCriteria = criteria.from(User.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.User.Columns.USERNAME), username));
            if (session.createQuery(criteria).getSingleResult() != null) {
                sessionProvider.closeSession();
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }
        return null;
    }
}
