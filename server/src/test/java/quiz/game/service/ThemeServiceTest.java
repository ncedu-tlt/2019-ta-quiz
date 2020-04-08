package quiz.game.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import quiz.game.model.entity.Theme;
import quiz.game.storage.ThemeStorage;

@SpringBootTest
public class ThemeServiceTest {
    @InjectMocks
    ThemeService themeService;

    @Mock
    ThemeStorage themeStorage;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllThemes() {

        //given
        List<Theme> themes = new ArrayList<>();
        Theme themeOne = new Theme();
        Theme themeTwo = new Theme();

        themeOne.setId(1);
        themeOne.setThemeName("History");
        themeTwo.setId(2);
        themeTwo.setThemeName("Geography");
        themes.add(themeOne);
        themes.add(themeTwo);

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
    public void getThemeById() {
        //given
        Theme themeOne = new Theme();

        themeOne.setId(1);
        themeOne.setThemeName("History");

        //when
        when(themeStorage.getThemeById(1)).thenReturn(themeOne);
        Theme result = themeService.getThemeById(1);

        //expect
        assertEquals(1, result.getId());
        assertEquals("History", result.getThemeName());
    }


    @Test
    public void addTheme() {
        //given
        List<Theme> themes = new ArrayList<>();
        Theme themeOne = new Theme();
        Theme themeTwo = new Theme();

        themeOne.setId(1);
        themeOne.setThemeName("History");
        themeTwo.setId(2);
        themeTwo.setThemeName("Geography");
        themes.add(themeOne);

        //when
        List<Theme> result = themeService.addTheme(themeTwo);

        //expect
        verify(themeStorage, times(1)).addTheme(themeTwo);

    }
}