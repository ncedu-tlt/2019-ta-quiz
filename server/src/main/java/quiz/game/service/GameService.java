package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quiz.game.model.Game;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.dto.ResultGameDTO;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.Score;
import quiz.game.model.entity.User;
import quiz.game.payload.response.MessageResponse;
import quiz.game.utils.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Service
public class GameService {
    @Autowired
    private ResultService resultService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private DifficultService difficultService;
    @Autowired
    private ScoreService scoreService;

    private PropertyReader prop = new PropertyReader();

    private HashMap<Long, Game> currentGames = new HashMap<>();

    public GameService() throws IOException {
    }

    public QuestionDTO start(int chosenThemeId, int chosenDifId, HttpServletRequest request) {
        Game game = new Game(chosenThemeId, chosenDifId, questionService.getQuestionsByThemeAndDifId(chosenThemeId, chosenDifId, prop.getQuestionsQuantity()));
        User user = userService.getUserFromJWT(request);
        Timer timer = new Timer(true);
        currentGames.put(user.getId(), game);
        GameCleaner gameCleaner = new GameCleaner(user.getId(), game);
        timer.schedule(gameCleaner, prop.getGameLiveTime() * 60 * 1000);
        return game.getNextQuestion();
    }

    class GameCleaner extends TimerTask {
        private Long userId;
        private Game game;
        public GameCleaner(Long userId, Game game) {
            this.userId = userId;
            this.game = game;
        }

        @Override
        public void run() {
            currentGames.remove(userId, game);
        }
    }

    public QuestionDTO getNextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        return currentGames.get(user.getId()).getNextQuestion();
    }

    public UUID getGameId(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        return currentGames.get(user.getId()).getGameId();
    }

    public ResultGameDTO getGameResults(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        saveGameResults(request);
        ResultGameDTO resultToFront = new ResultGameDTO();
        resultToFront.setQuestions(resultService.getResultsByGameId(currentGames.get(user.getId()).getGameId()));
        resultToFront.setScore(currentGames.get(user.getId()).getScore());
        currentGames.remove(user.getId());
        return resultToFront;
    }

    private void saveGameResults(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        List<Result> results = currentGames.get(user.getId()).getUserAnswers();
        for (Result result : results) {
            resultService.saveUserAnswer(result);
        }
        countScore(currentGames.get(user.getId()));
        Date date = new Date();
        Score score = new Score(currentGames.get(user.getId()).getGameId(), user, currentGames.get(user.getId()).getScore(), date);
        scoreService.saveScore(score);
    }

    public ResponseEntity<?> addUserAnswer(Result userAnswer, HttpServletRequest request) {
        boolean foundAnswer = false;
        for (AnswerDTO answer : currentGames.get(userService.getUserFromJWT(request).getId()).getQuestionList().get(
                currentGames.get(userService.getUserFromJWT(request).getId()).getProgress()-1).getAnswers()) {
            if (answer.getId() == userAnswer.getAnswer().getId()) {
                foundAnswer = true;
            }
        }
        if (!foundAnswer) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Answer doesn't match provided options"));
        } else {
            currentGames.get(userService.getUserFromJWT(request).getId()).addUserAnswer(userAnswer);
            return ResponseEntity.ok(new MessageResponse("Answer saved"));
        }
    }

    public void countScore(Game game) {
        int points = difficultService.getDifficultById(game.getChosenDifId()).getDifficultFactor();
        for (Result result: game.getUserAnswers()) {
           if (result.getAnswer().getAnswerIsCorrect()) {
               game.setScore(game.getScore() + (points * 100));
           }
        }
    }


}

