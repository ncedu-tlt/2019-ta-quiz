package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.entity.Difficult;
import quiz.game.storage.DifficultStorage;

import java.util.List;

@Service
public class DifficultService {

    @Autowired
    private DifficultStorage difficultStorage;

    public List<Difficult> getAllDifficult() {

        return difficultStorage.getAllDifficult();
    }

    public List<Difficult> addDifficult(Difficult difficult) {

        return difficultStorage.addDifficult(difficult);
    }

    public Difficult getDifficultById(int id) {
        return difficultStorage.getDifficultById(id);
    }
}
