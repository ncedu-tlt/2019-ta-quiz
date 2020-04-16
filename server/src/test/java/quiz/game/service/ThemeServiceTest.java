package quiz.game.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import quiz.game.model.entity.Theme;
import quiz.game.storage.ThemeStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThemeServiceTest {
    @InjectMocks
    ThemeService themeService;

    @Mock
    ThemeStorage themeStorage;

    @Before
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllThemes() {

        //given
        List<Theme> themes = Arrays.asList(new Theme(1, "History"), new Theme(2, "Geography"));

        //when
        when(themeStorage.getAllThemes()).thenReturn(themes);
        List<Theme> result = themeService.getAllThemes();

        //expect
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getId());
        assertEquals("History", result.get(0).getThemeName());
        assertEquals(2, result.get(1).getId());
        assertEquals("Geography", result.get(1).getThemeName());
        verify(themeStorage, times(1)).getAllThemes();
    }


    @Test
    void getThemeById() {
        //given
        Theme themeOne = new Theme(1, "History");

        //when
        when(themeStorage.getThemeById(1)).thenReturn(themeOne);
        Theme result = themeService.getThemeById(1);

        //expect
        assertEquals(1, result.getId());
        assertEquals("History", result.getThemeName());
    }


    @Test
    void addTheme() {
        //given
        List<Theme> themes = new ArrayList<>();
        Theme themeOne = new Theme(1, "History");
        Theme themeTwo = new Theme(2, "Geography");
        themes.add(themeOne);

        //when
        List<Theme> result = themeService.addTheme(themeTwo);

        //expect
        verify(themeStorage, times(1)).addTheme(themeTwo);

    }
}