package quiz.game.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import quiz.game.model.entity.Theme;
import quiz.game.service.ThemeService;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ThemeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ThemeService themeService;

    @Test
    void getAllThemes_OneTheme() throws Exception {
        //given
        Theme theme = new Theme(1,"it");
        given(themeService.getAllThemes()).willReturn(Arrays.asList(theme));

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/theme"));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].themeName", is("it")));
    }

    @Test
    void addTheme() {
    }

    @Test
    void getThemeById_One() throws Exception {
        //given
        Theme theme = new Theme(1, "it");
        given(themeService.getThemeById(1)).willReturn(theme);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/theme/{id}", 1));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.themeName", is("it")));
    }
}
