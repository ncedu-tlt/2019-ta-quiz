package quiz.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import quiz.game.model.entity.Theme;
import quiz.game.storage.ThemeStorage;

import java.util.List;

@RestController
public class ThemeController {

    @Autowired
    private ThemeStorage themeStorage;

    @GetMapping(value = "/theme")
    public List<Theme> getAllThemes() {
        return themeStorage.getAllThemes();
    }

    @PostMapping(value = "/theme/add")
    public List<Theme> addThemes(@RequestBody Theme theme) {
        return  themeStorage.addTheme(theme);
    }
}
