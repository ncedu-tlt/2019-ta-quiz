package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quiz.game.model.entity.Difficult;
import quiz.game.service.DifficultService;
import quiz.game.storage.DifficultStorage;

import java.util.List;

@RestController
public class DifficultController {

    @Autowired
    private DifficultService service;

    @GetMapping(value = "/difficult")
    public List<Difficult> getAllDifficult() {
        return service.getAllDifficult();
    }

    @PostMapping(value = "difficult/add")
    public List<Difficult> addDifficult(@RequestBody Difficult difficult) {

        return service.addDifficult(difficult);
    }
}
