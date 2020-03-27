package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import quiz.game.model.entity.Theme;
import quiz.game.service.ThemeService;

import java.util.List;

@CrossOrigin
@RestController
public class ThemeController {

    @Autowired
    private ThemeService service;

    @GetMapping(value = "/theme")
    public List<Theme> getAllThemes() {

        return service.getAllThemes();
    }

    @PostMapping(value = "/theme/add")
    public List<Theme> addTheme(@RequestBody Theme theme) {

        return  service.addTheme(theme);
    }

    @GetMapping(value = "/theme/{id}")
    public Theme getThemeById(@PathVariable int id) {
        return service.getThemeById(id);
    }
}
