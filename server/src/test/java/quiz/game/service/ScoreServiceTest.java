package quiz.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import quiz.game.model.dto.ScoreDTO;
import quiz.game.model.dto.StatisticDTO;
import quiz.game.model.entity.*;
import quiz.game.storage.ScoreStorage;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.crypto.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ScoreServiceTest {
    @InjectMocks
    ScoreService scoreService;

    @Mock
    ScoreStorage scoreStorage;

    @Mock
    UserService userService;

    @Mock
    ResultService resultService;

    @Mock
    ThemeService themeService;

    @Mock
    DifficultService difficultService;
/*
    @Test
    void saveScore() {
    }


 */
    @Test
    void getScoresByUserId() {
        //given
        HttpServletRequest request = new MockRequest();
        UUID idGame = UUID.randomUUID();
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd-mm-yyyy");
        User user = new User(2L, "user", "123");
        List<Score> scores = Arrays.asList(new Score(idGame, user,100, date, new Theme(1, "History"), new Difficult(1, "Easy", 1)));

        //when
        when(userService.getUserFromJWT(request)).thenReturn(user);
        when(scoreStorage.getScoresByUserId(userService.getUserFromJWT(request).getId())).thenReturn(scores);
        List<ScoreDTO> result = scoreService.getScoresByUserId(request);

        //expect
        assertEquals(idGame, result.get(0).getIdGame());
        assertEquals(100, result.get(0).getScore());
        assertEquals(df.format(date), result.get(0).getDate());
        assertEquals("History", result.get(0).getTheme());
        assertEquals("Easy", result.get(0).getDifficult());
    }

    @Test
    void getStatistic() {
        //given
        HttpServletRequest request = new MockRequest();
        User user = new User(2L, "user", "123");
        Question questionOne = new Question(1, "Who?", new Theme(1, "History"), new Difficult(1, "Easy", 1));
        Answer answer = new Answer( 1, "answer1", true, questionOne);
        UUID id = UUID.randomUUID();
        Date date = new Date();
        UUID idGame = UUID.randomUUID();
        List<Result> results = Arrays.asList(new Result(id, date, idGame, user, answer));
        List<Theme> themes = Arrays.asList(new Theme(1, "History"), new Theme(2, "Geography"));
        List<Difficult> difficults = Arrays.asList(new Difficult(1, "Easy", 1));

        //when
        when(userService.getUserFromJWT(request)).thenReturn(user);
        when(scoreStorage.getUserGamesCount(user.getId())).thenReturn(1L);
        when(scoreStorage.getUserSumScore(user.getId())).thenReturn(100);
        when(resultService.getResultsByUserId(user.getId())).thenReturn(results);
        when(themeService.getAllThemes()).thenReturn(themes);
        when(difficultService.getAllDifficult()).thenReturn(difficults);
        when(scoreStorage.getUserGamesCount(user.getId(), 1, 1)).thenReturn(1L);
        when(scoreStorage.getUserSumScore(user.getId(), 1, 1)).thenReturn(100);
        StatisticDTO result = scoreService.getStatistic(request);

        //expect
        assertEquals(1, (long)result.getTotalGames());
        assertEquals(100, result.getTotalScore());
        assertEquals(100, result.getRightAnswerPercent(),0);
        assertEquals("History", result.getSpecialty().get(0).getTheme());
        assertEquals("Easy", result.getSpecialty().get(0).getDifficult());
        assertEquals(1, (long)result.getSpecialty().get(0).getTotalGames());
        assertEquals(100, (long)result.getSpecialty().get(0).getTotalScore());
        assertEquals(100, result.getSpecialty().get(0).getRightAnswerPercent(), 0);
        assertEquals("Geography", result.getSpecialty().get(1).getTheme());
        assertEquals("Easy", result.getSpecialty().get(1).getDifficult());
        assertEquals(0, (long)result.getSpecialty().get(1).getTotalGames());
        assertEquals(0, (long)result.getSpecialty().get(1).getTotalScore());
        assertEquals(0, result.getSpecialty().get(1).getRightAnswerPercent(), 0);
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