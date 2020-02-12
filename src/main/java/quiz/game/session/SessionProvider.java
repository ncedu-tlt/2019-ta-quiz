package quiz.game.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Component
public class SessionProvider {

    private static final ThreadLocal<Session> SESSION_THREAD_LOCAL = new ThreadLocal<>();

    @PersistenceUnit
    EntityManagerFactory entityManagerFactory;
    public Session getSession() {
        Session session = SESSION_THREAD_LOCAL.get();
        if (session == null) {
            session = entityManagerFactory
                    .unwrap(SessionFactory.class)
                    .openSession();
        }
        return session;
    }

    public static void closeSession() {
        Session session = SESSION_THREAD_LOCAL.get();
        if (session != null) {
            session.close();
            SESSION_THREAD_LOCAL.remove();
        }
    }
}
