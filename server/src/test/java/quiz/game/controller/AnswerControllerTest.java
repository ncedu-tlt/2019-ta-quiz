package quiz.game.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.entity.Answer;
import quiz.game.model.entity.Difficult;
import quiz.game.model.entity.Question;
import quiz.game.model.entity.Theme;
import quiz.game.service.AnswerService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AnswerService answerService;
/*
    @Test
    void getAllAnswers_One() throws Exception {

        //given
        Answer answer = new Answer();
        Theme theme = new Theme();
        Difficult difficult = new Difficult();
        Question question = new Question();
        answer.setId(1);
        answer.setAnswerText("you");
        answer.setAnswerIsCorrect(true);
        answer.setQuestion(question);
        question.setId(1);
        question.setQuestionName("who?");
        question.setDifficult(difficult);
        question.setTheme(theme);
        difficult.setId(1);
        difficult.setDifficultName("norm");
        difficult.setDifficultFactor(1);
        theme.setId(1);
        theme.setThemeName("it");
        given(answerService.getAllAnswers()).willReturn(Arrays.asList(answer));

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/answers"));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].answerText", is("you")))
                .andExpect(jsonPath("$[0].answerIsCorrect", is(true)))
                .andExpect(jsonPath("$[0].question.id", is(1)))
                .andExpect(jsonPath("$[0].question.questionName", is("who?")))
                .andExpect(jsonPath("$[0].question.theme.id", is(1)))
                .andExpect(jsonPath("$[0].question.difficult.id", is(1)))
                .andExpect(jsonPath("$[0].question.theme.themeName", is("it")))
                .andExpect(jsonPath("$[0].question.difficult.difficultName", is("norm")))
                .andExpect(jsonPath("$[0].question.difficult.difficultFactor", is(1)));
    }
 */
/*
    @Test
    void getAllAnswersByQuestionId_OneId() throws Exception {

        //given
        Answer answer = new Answer(1, "you", true,
                new Question(1, "who?", new Theme(1, "it"), new Difficult(1, "norm", 1)));

        given(answerService.getAllAnswersByQuestionId(1)).willReturn(Collections.singletonList(answer));

        //when
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/answers/{id}/correct", 1));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].answerText", is("you")))
                .andExpect(jsonPath("$[0].answerIsCorrect", is(true)))
                .andExpect(jsonPath("$[0].question.id", is(1)))
                .andExpect(jsonPath("$[0].question.questionName", is("who?")))
                .andExpect(jsonPath("$[0].question.theme.id", is(1)))
                .andExpect(jsonPath("$[0].question.difficult.id", is(1)))
                .andExpect(jsonPath("$[0].question.theme.themeName", is("it")))
                .andExpect(jsonPath("$[0].question.difficult.difficultName", is("norm")))
                .andExpect(jsonPath("$[0].question.difficult.difficultFactor", is(1)));
    }
 */

    @Test
    void getAllAnswersByQuestionIdWOCorrect_Answers() throws Exception {

        //given
       List<AnswerDTO> answer = Arrays.asList(new AnswerDTO(1,"test"));
        given(answerService.getAllAnswersByQuestionIdWOCorrect(1)).willReturn(answer);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/answers/{id}", 1));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].answerText", is("test")));

    }
}
