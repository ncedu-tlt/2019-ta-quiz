package quiz.game;

public interface DbConsts {

    interface Question {

        String NAME = "question";

        interface Columns {
            String ID = "id";
            String QUESTION_NAME = "name_question";
            String THEME_ID = "id_theme";
            String DIFFICULT_ID = "id_difficult";
        }
    }

    interface Theme {
        String NAME = "theme";

        interface Columns {
            String ID = "id";
            String THEME_NAME = "name_theme";
        }
    }

    interface Difficult {
        String NAME = "difficult";

        interface Columns {
            String ID = "id";
            String DIFFICULT_NAME = "name_difficult";
            String DIFFICULT_FACTOR = "factor_difficult";
        }
    }

    interface Score {
        String NAME = "score";

        interface Columns {
            String GAME_ID = "id_game";
            String USER_ID = "id_user";
            String SCORE = "score";
            String DATE = "date";
            String THEME_ID = "id_theme";
            String DIFFICULT_ID = "id_difficult";
        }
    }

    interface Answer {
        String NAME = "answer";

        interface Columns {
            String ID = "id";
            String ANSWER_TEXT = "text_answer";
            String ANSWER_IS_CORRECT = "iscorrect_answer";
            String QUESTION_ID = "id_question";
        }
    }

    interface User {
        String NAME = "users";

        interface Columns {
            String ID = "id";
            String USERNAME = "username";
            String PASSWORD = "password";
        }
    }

    interface Role {
        String NAME = "role";

        interface Columns {
            String ID = "id";
            String NAME = "name";
        }
    }

    interface Result {
        String NAME = "result";

        interface Columns {
            String ID = "id";
            String DATE = "date";
            String GAME_ID = "id_game";
            String USER_ID = "id_user";
            String ANSWER_ID = "id_answer";
        }
    }
}
