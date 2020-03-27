package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.DbConsts;
import quiz.game.model.entity.Theme;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ThemeStorage {

    private final SessionProvider sessionProvider;

    public ThemeStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public List<Theme> getAllThemes() {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Theme> criteria = builder.createQuery(Theme.class);
        Root<Theme> rootCriteria = criteria.from(Theme.class);
        criteria.select(rootCriteria);
        List<Theme> result = session.createQuery(criteria).getResultList();
        sessionProvider.closeSession();
        return result;
    }

    public List<Theme> addTheme(Theme theme) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(theme);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return getAllThemes();
    }

    public Theme getThemeById(int id) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Theme> criteria = builder.createQuery(Theme.class);
        Root<Theme> rootCriteria = criteria.from(Theme.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get(DbConsts.Theme.Columns.ID), id));
        Theme result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }
}
