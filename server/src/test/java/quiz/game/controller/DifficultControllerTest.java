package quiz.game.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import quiz.game.model.entity.Difficult;
import quiz.game.service.DifficultService;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DifficultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DifficultService difficultService;

    @Test
    public void getAllDifficult_OneDifficult() throws Exception {

        //given
        Difficult difficult = new Difficult();
        difficult.setId(1);
        difficult.setDifficultName("norm");
        difficult.setDifficultFactor(1);
        given(difficultService.getAllDifficult()).willReturn(Arrays.asList(difficult));

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/difficult"));

        //then
        resultActions.andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].difficultName", is("norm")))
                    .andExpect(jsonPath("$[0].difficultFactor", is(1)));
    }

    @Test
    public void getDifficultById_One() throws Exception {
        //given
        Difficult difficult = new Difficult();
        difficult.setId(1);
        difficult.setDifficultName("norm");
        difficult.setDifficultFactor(1);
        given(difficultService.getDifficultById(1)).willReturn(difficult);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/difficult/{id}", 1));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk());
    }

        @Test
        public void  addDifficult_Create() throws Exception {

       }
}
