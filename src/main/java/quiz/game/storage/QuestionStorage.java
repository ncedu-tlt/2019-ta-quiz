package quiz.game.storage;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import quiz.game.session.SessionProvider;
import quiz.game.model.Question;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class QuestionStorage {

    private final SessionProvider sessionProvider;

    public QuestionStorage(SessionProvider sessionProvider) {
        this.sessionProvider = sessionProvider;
    }

    public List<Question> getAllQuestions() {
        Session session = sessionProvider.getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
        Root<Question> rootCriteria = criteria.from(Question.class);
        criteria.select(rootCriteria);
        return session.createQuery(criteria).getResultList();
    }


    public List<Question> addQuestion(Question question) {
        Session session = sessionProvider.getSession();
        session.beginTransaction();
        session.saveOrUpdate(question);
        session.getTransaction().commit();
        sessionProvider.closeSession();
        return getAllQuestions();
    }
}
