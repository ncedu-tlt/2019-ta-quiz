package quiz.game.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import quiz.game.model.Game;
import quiz.game.model.dto.AnswerDTO;
import quiz.game.model.dto.ResultGameDTO;
import quiz.game.model.dto.QuestionDTO;
import quiz.game.model.dto.ResultQuestionDTO;
import quiz.game.model.entity.Result;
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
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private DifficultService difficultService;
    private PropertyReader prop = new PropertyReader();

    private HashMap<Long, Game> currentGames = new HashMap<>();

    public GameService() throws IOException {
    }

    public QuestionDTO start(int chosenThemeId, int chosenDifId, HttpServletRequest request) {
        Game game = new Game();
        User user = userService.getUserFromJWT(request);
        Timer timer = new Timer(true);
        game.setChosenThemeId(chosenThemeId);
        game.setChosenDifId(chosenDifId);
        game.setQuestionList(questionService.getQuestionsByThemeAndDifId(chosenThemeId, chosenDifId, prop.getQuestionsQuantity()));
        game.setGameId(UUID.randomUUID());
        game.setProgress(0);
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
            //Date date = new Date();
            //System.out.println(date + " Game deleted!");
            currentGames.remove(userId, game);
        }
    }
/*
    public QuestionDTO getNextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        if (currentGames.get(user.getId()).getProgress() != currentGames.get(user.getId()).getQuestionList().size()) {

            return nextQuestion(request);
        } else {
            return endGame(request);
        }
    }
 */

    public QuestionDTO getNextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        return currentGames.get(user.getId()).getNextQuestion();
    }

    public UUID getGameId(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        return currentGames.get(user.getId()).getGameId();
    }

/*
    private QuestionDTO nextQuestion(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        int progress = currentGames.get(user.getId()).getProgress();
        QuestionDTO question = currentGames.get(user.getId()).getQuestionList().get(progress);
        question.setProgress(progress+1 + "/" + currentGames.get(user.getId()).getQuestionList().size());
        currentGames.get(user.getId()).setProgress(progress+1);
        return question;
    }

    private QuestionDTO endGame(HttpServletRequest request) {
        QuestionDTO question = new QuestionDTO();
        question.setId(-1);
        return question;
    }
    */

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

    public ResultGameDTO getGameResults(HttpServletRequest request) {
        User user = userService.getUserFromJWT(request);
        saveGameResults(request);
        ResultGameDTO resultToFront = new ResultGameDTO();
        List<ResultQuestionDTO> questionsDTO= new ArrayList<>();
        questionsDTO = resultService.getResultsByGameId(currentGames.get(user.getId()).getGameId());
        resultToFront.setQuestions(questionsDTO);
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

