package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.model.entity.Result;
import quiz.game.session.SessionProvider;

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
}
