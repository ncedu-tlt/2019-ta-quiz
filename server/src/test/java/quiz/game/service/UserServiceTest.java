package quiz.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import quiz.game.model.entity.ERole;
import quiz.game.model.entity.Role;
import quiz.game.model.entity.User;
import quiz.game.payload.request.LoginRequest;
import quiz.game.payload.request.SignupRequest;
import quiz.game.payload.response.JwtResponse;
import quiz.game.security.jwt.JwtUtils;
import quiz.game.storage.RoleStorage;
import quiz.game.storage.UserStorage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserStorage userStorage;

    @Mock
    RoleStorage roleStorage;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    PasswordEncoder encoder;

    @Mock
    AuthenticationManager authenticationManager;

    @Test
    void getUserByUsername() {
        //given
        User user = new User(2L, "user", "123");

        //when
        when(userStorage.findByUsername("user")).thenReturn(user);
        User result = userService.getUserByUsername("user");

        //expect
        assertEquals(2, (long)result.getId());
        assertEquals("user", result.getUsername());
    }

    @Test
    void getUserFromJWT() {
        //given
        HttpServletRequest request = new MockRequest();
        User user = new User(2L, "user", "123");

        //when
        when(userStorage.findByUsername("user")).thenReturn(user);
        when(jwtUtils.getUserNameFromJwtToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTg3MjMxNjEwLCJleHAiOjE1ODczMTgwMTB9.qiSxguq5-bQuiA7aknyv5EIo1yvMva2qYRzsMHiggCSJM9Oxh1kr1bR4Bcn9TuWJQ0Za6DZiexI6orJqh7WYmw")).thenReturn("user");
        User result = userService.getUserFromJWT(request);

        //expect
        assertEquals("user", result.getUsername());
    }

    @Test
    void registerUser() {
        //given

        SignupRequest signUpRequest = new SignupRequest("user", null, "123");
        User user = new User(2L, "user", "123");
        Role role = new Role(1, ERole.ROLE_USER);

        //when
        when(userStorage.existsByUsername(signUpRequest.getUsername())).thenReturn(false);
        when(userStorage.findByUsername("user")).thenReturn(user);
        when(encoder.encode(signUpRequest.getPassword())).thenReturn("123");
        when(roleStorage.findByName(ERole.ROLE_USER)).thenReturn(role);
        ResponseEntity result = userService.registerUser(signUpRequest);

        //expect
        assertEquals("200 OK", result.getStatusCode().toString());
        assertEquals("MessageResponse(message=User registered successfully!)", result.getBody().toString());
    }

/*
    @Test
    void loadUserByUsername() {
    }

 */

    @Test
    void authenticateUser() {
        //given
        User user = new User(2L, "user", "123");
        LoginRequest loginRequest = new LoginRequest("user", "123");
        Authentication authentication = new MockAuth();
        String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTg3MjMxNjEwLCJleHAiOjE1ODczMTgwMTB9.qiSxguq5-bQuiA7aknyv5EIo1yvMva2qYRzsMHiggCSJM9Oxh1kr1bR4Bcn9TuWJQ0Za6DZiexI6orJqh7WYmw";

        //when
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()))).thenReturn(authentication);
        when(jwtUtils.generateJwtToken(authentication)).thenReturn(jwt);
        ResponseEntity result = userService.authenticateUser(loginRequest);

        //expect
        assertEquals("200 OK", result.getStatusCode().toString());
    }

    private class MockAuth implements Authentication {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return UserDetailsImpl.build(new User(1L, "user","123"));
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean b) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return null;
        }
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
            return
                    "Bearer " +
                            "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNTg3MjMxNjEwLCJleHAiOjE1ODczMTgwMTB9.qiSxguq5-bQuiA7aknyv5EIo1yvMva2qYRzsMHiggCSJM9Oxh1kr1bR4Bcn9TuWJQ0Za6DZiexI6orJqh7WYmw";
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