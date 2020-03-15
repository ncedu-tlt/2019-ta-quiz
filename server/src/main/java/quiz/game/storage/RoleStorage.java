package quiz.game.storage;


import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.model.entity.ERole;
import quiz.game.model.entity.Role;
import quiz.game.session.SessionProvider;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Component
public class RoleStorage {
    private final SessionProvider sessionProvider;

    public RoleStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public Role findByName(ERole role) {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> criteria = builder.createQuery(Role.class);
        Root<Role> rootCriteria = criteria.from(Role.class);
        criteria.select(rootCriteria).where(builder.equal(rootCriteria.get("name"),role));
        Role result = session.createQuery(criteria).getSingleResult();
        sessionProvider.closeSession();
        return result;
    }
}
