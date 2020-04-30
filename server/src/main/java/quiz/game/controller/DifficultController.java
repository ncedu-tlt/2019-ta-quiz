package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.entity.Difficult;
import quiz.game.service.DifficultService;

import java.util.List;

@CrossOrigin
@RestController
public class DifficultController {

    @Autowired
    private DifficultService service;

    @GetMapping(value = "/difficult")
    public List<Difficult> getAllDifficult() {
        return service.getAllDifficult();
    }
/*
    @PostMapping(value = "difficult/add")
    public List<Difficult> addDifficult(@RequestBody Difficult difficult) {

        return service.addDifficult(difficult);
    }
 */

    @GetMapping(value = "/difficult/{id}")
    public Difficult getDifficultById(@PathVariable int id) {
        return service.getDifficultById(id);
    }
}
