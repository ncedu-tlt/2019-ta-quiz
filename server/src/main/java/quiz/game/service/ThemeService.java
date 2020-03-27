package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.entity.Theme;
import quiz.game.storage.ThemeStorage;

import java.util.List;

@Service
public class ThemeService {

    @Autowired
    private ThemeStorage themeStorage;

    public List<Theme> getAllThemes() {

        return themeStorage.getAllThemes();
    }

    public List<Theme> addTheme(Theme theme) {

        return  themeStorage.addTheme(theme);
    }

    public Theme getThemeById(int id) {
        return themeStorage.getThemeById(id);
    }
}

