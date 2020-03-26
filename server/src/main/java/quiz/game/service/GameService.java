package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quiz.game.model.Game;
import quiz.game.model.dto.GameDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.entity.Result;
import quiz.game.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private ResultService resultService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;

    private HashMap<Long, Game> currentGames = new HashMap<>();

    public QuestionDTO start(int chosenThemeId, int chosenDifId,int qty, HttpServletRequest request) {
        Game game = new Game();
        game.setChosenThemeId(chosenThemeId);
        game.setChosenDifId(chosenDifId);
        game.setQuestionList(questionService.getQuestionsByThemeAndDifId(chosenThemeId, chosenDifId, qty));
        game.setGameId(UUID.randomUUID());
        game.setProgress(0);
        currentGames.put(userService.getUserFromJWT(request).getId(), game);
        return getNextQuestion(request);
    }

    public QuestionDTO getNextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        if (currentGames.get(user.getId()).getProgress() != currentGames.get(user.getId()).getQuestionList().size()) {

            return nextQuestion(request);
        } else {
            return endGame(request);
        }
    }

    public UUID getGameId(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        return currentGames.get(user.getId()).getGameId();
    }

    private QuestionDTO nextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        int progress = currentGames.get(user.getId()).getProgress();
        QuestionDTO question = currentGames.get(user.getId()).getQuestionList().get(progress);
        currentGames.get(user.getId()).setProgress(progress+1);
        return question;
    }

    private QuestionDTO endGame(HttpServletRequest request) {
        QuestionDTO question = new QuestionDTO();
        question.setId(-1);
        return question;
    }

/*
    public GameDTO getGameResults(HttpServletRequest request) {
        GameDTO result = new GameDTO();
        User user = userService.getUserFromJWT(request);
        result.setIdGame(currentGames.get(user.getId()).getGameId());
        result.setResults(resultService.getResultsByGameId(result.getIdGame()));
        //result.setScore(game.getScore());
        return result;
    }
 */

    public GameDTO getGameResults(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        List<Result> results = currentGames.get(user.getId()).getUserAnswers();
        for (Result result : results) {
            resultService.saveUserAnswer(result);
        }
        GameDTO resultToFront = new GameDTO();
        resultToFront.setIdGame(currentGames.get(user.getId()).getGameId());
        resultToFront.setResults(resultService.getResultsByGameId(resultToFront.getIdGame()));
        return resultToFront;
    }

    public void addUserAnswer(Result userAnswer, HttpServletRequest request) {
        currentGames.get(userService.getUserFromJWT(request).getId()).addUserAnswer(userAnswer);
    }
/*
    public void setScore(int idAnswer) {
        Answer answer = answerService.getAnswerById(idAnswer);
        int points = answer.getQuestion().getDifficult().getDifficultFactor();
        if (answer.isAnswerIsCorrect()) {
            game.setScore(game.getScore() + (points *100));
        }
    }
    */

}

