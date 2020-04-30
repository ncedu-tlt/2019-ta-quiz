package quiz.game.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import quiz.game.model.dto.ResultAnswerDTO;
import quiz.game.model.dto.ResultGameDTO;
import quiz.game.model.dto.ResultQuestionDTO;
import quiz.game.model.dto.ScoreDTO;
import quiz.game.model.entity.*;
import quiz.game.service.GameService;
import quiz.game.service.ResultService;
import quiz.game.service.ScoreService;
import quiz.game.service.ThemeService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ResultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ResultService resultService;

    @MockBean
    ScoreService scoreService;

    @MockBean
    GameService gameService;

    @Test
    void addUserAnswer() {
    }

    @Test
    void getLastGameResults() throws Exception{
        //given
        HttpServletRequest request = new MockRequest();


        ResultAnswerDTO answerOne = new ResultAnswerDTO("answer1", true, true);
        ResultAnswerDTO answerTwo = new ResultAnswerDTO("answer2", false, false);
        List<ResultAnswerDTO> answerListOne = Arrays.asList(answerOne, answerTwo);
        List<ResultQuestionDTO> questions = Arrays.asList(new ResultQuestionDTO("Who?", answerListOne));
        ResultGameDTO result = new ResultGameDTO(100, questions);

        given(gameService.getGameResults(any(HttpServletRequest.class))).willReturn(result);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/results"));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(100)))
                .andExpect(jsonPath("$.questions").isArray())
                .andExpect(jsonPath("$.questions[0].questionName", is("Who?")))
                .andExpect(jsonPath("$.questions[0].answers").isArray())
                .andExpect(jsonPath("$.questions[0].answers[0].answerText", is("answer1")))
                .andExpect(jsonPath("$.questions[0].answers[0].answerIsCorrect", is(true)))
                .andExpect(jsonPath("$.questions[0].answers[0].userAnswer", is(true)))
                .andExpect(jsonPath("$.questions[0].answers[1].answerText", is("answer2")))
                .andExpect(jsonPath("$.questions[0].answers[1].answerIsCorrect", is(false)))
                .andExpect(jsonPath("$.questions[0].answers[1].userAnswer", is(false)))
        ;
    }

    @Test
    void getResultsByGameId() throws Exception{
        //given
        UUID gameID = UUID.randomUUID();

        ResultAnswerDTO answerOne = new ResultAnswerDTO("answer1", true, true);
        ResultAnswerDTO answerTwo = new ResultAnswerDTO("answer2", false, false);
        ResultAnswerDTO answerThree = new ResultAnswerDTO("answer3",true, false);
        ResultAnswerDTO answerFour = new ResultAnswerDTO("answer4", false, true);

        List<ResultAnswerDTO> answerListOne = Arrays.asList(answerOne, answerTwo);
        List<ResultAnswerDTO> answerListTwo = Arrays.asList(answerThree, answerFour);

        List<ResultQuestionDTO> resultList = Arrays.asList(
                new ResultQuestionDTO("Who?", answerListOne),
                new ResultQuestionDTO("What?", answerListTwo)
        );
        given(resultService.getResultsByGameId(gameID)).willReturn(resultList);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/results/{id}", gameID));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].questionName", is("Who?")))
                .andExpect(jsonPath("$[0].answers").isArray())
                .andExpect(jsonPath("$[0].answers[0].answerText", is("answer1")))
                .andExpect(jsonPath("$[0].answers[0].answerIsCorrect", is(true)))
                .andExpect(jsonPath("$[0].answers[0].userAnswer", is(true)))
                .andExpect(jsonPath("$[0].answers[1].answerText", is("answer2")))
                .andExpect(jsonPath("$[0].answers[1].answerIsCorrect", is(false)))
                .andExpect(jsonPath("$[0].answers[1].userAnswer", is(false)))
                .andExpect(jsonPath("$[1].questionName", is("What?")))
                .andExpect(jsonPath("$[1].answers").isArray())
                .andExpect(jsonPath("$[1].answers[0].answerText", is("answer3")))
                .andExpect(jsonPath("$[1].answers[0].answerIsCorrect", is(true)))
                .andExpect(jsonPath("$[1].answers[0].userAnswer", is(false)))
                .andExpect(jsonPath("$[1].answers[1].answerText", is("answer4")))
                .andExpect(jsonPath("$[1].answers[1].answerIsCorrect", is(false)))
                .andExpect(jsonPath("$[1].answers[1].userAnswer", is(true)))
        ;
    }

    @Test
    void getUserGames() throws Exception{
        //given
        HttpServletRequest request = new MockRequest();
        UUID gameID = UUID.randomUUID();
        Date date = new Date();
        List<ScoreDTO> resultList = Arrays.asList(new ScoreDTO(gameID, date.toString(), "History", "Easy", 100));

        given(scoreService.getScoresByUserId(any(HttpServletRequest.class))).willReturn(resultList);

        //when
        ResultActions resultActions = this.mockMvc.perform(get("/results/all"));

        //then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].idGame", is(gameID.toString())))
                .andExpect(jsonPath("$[0].date", is(date.toString())))
                .andExpect(jsonPath("$[0].theme", is("History")))
                .andExpect(jsonPath("$[0].difficult", is("Easy")))
                .andExpect(jsonPath("$[0].score", is(100)))
        ;
    }

    @Test
    void getStatistic() {
    }

    private class MockRequest implements HttpServletRequest {

        @Override
        public String getAuthType() {
            return null;
        }

        @Override
        public Cookie[] getCookies() {
            return new Cookie[0];
        }

        @Override
        public long getDateHeader(String s) {
            return 0;
        }

        @Override
        public String getHeader(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaders(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            return null;
        }

        @Override
        public int getIntHeader(String s) {
            return 0;
        }

        @Override
        public String getMethod() {
            return null;
        }

        @Override
        public String getPathInfo() {
            return null;
        }

        @Override
        public String getPathTranslated() {
            return null;
        }

        @Override
        public String getContextPath() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public String getRemoteUser() {
            return null;
        }

        @Override
        public boolean isUserInRole(String s) {
            return false;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public String getRequestedSessionId() {
            return null;
        }

        @Override
        public String getRequestURI() {
            return null;
        }

        @Override
        public StringBuffer getRequestURL() {
            return null;
        }

        @Override
        public String getServletPath() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean b) {
            return null;
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public String changeSessionId() {
            return null;
        }

        @Override
        public boolean isRequestedSessionIdValid() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromCookie() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromURL() {
            return false;
        }

        @Override
        public boolean isRequestedSessionIdFromUrl() {
            return false;
        }

        @Override
        public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
            return false;
        }

        @Override
        public void login(String s, String s1) throws ServletException {

        }

        @Override
        public void logout() throws ServletException {

        }

        @Override
        public Collection<Part> getParts() throws IOException, ServletException {
            return null;
        }

        @Override
        public Part getPart(String s) throws IOException, ServletException {
            return null;
        }

        @Override
        public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String getCharacterEncoding() {
            return null;
        }

        @Override
        public void setCharacterEncoding(String s) throws UnsupportedEncodingException {

        }

        @Override
        public int getContentLength() {
            return 0;
        }

        @Override
        public long getContentLengthLong() {
            return 0;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            return null;
        }

        @Override
        public String getParameter(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getParameterNames() {
            return null;
        }

        @Override
        public String[] getParameterValues(String s) {
            return new String[0];
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            return null;
        }

        @Override
        public String getProtocol() {
            return null;
        }

        @Override
        public String getScheme() {
            return null;
        }

        @Override
        public String getServerName() {
            return null;
        }

        @Override
        public int getServerPort() {
            return 0;
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return null;
        }

        @Override
        public String getRemoteAddr() {
            return null;
        }

        @Override
        public String getRemoteHost() {
            return null;
        }

        @Override
        public void setAttribute(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public Locale getLocale() {
            return null;
        }

        @Override
        public Enumeration<Locale> getLocales() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public RequestDispatcher getRequestDispatcher(String s) {
            return null;
        }

        @Override
        public String getRealPath(String s) {
            return null;
        }

        @Override
        public int getRemotePort() {
            return 0;
        }

        @Override
        public String getLocalName() {
            return null;
        }

        @Override
        public String getLocalAddr() {
            return null;
        }

        @Override
        public int getLocalPort() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public AsyncContext startAsync() throws IllegalStateException {
            return null;
        }

        @Override
        public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
            return null;
        }

        @Override
        public boolean isAsyncStarted() {
            return false;
        }

        @Override
        public boolean isAsyncSupported() {
            return false;
        }

        @Override
        public AsyncContext getAsyncContext() {
            return null;
        }

        @Override
        public DispatcherType getDispatcherType() {
            return null;
        }
    }
}