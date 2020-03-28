package quiz.game.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private int questionsQuantity;
    private int gameLiveTime;
    private InputStream inputStream;

    public PropertyReader() throws IOException {
        this.getPropValues();
    }

    public void getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "game.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property value
            this.questionsQuantity = Integer.parseInt(prop.getProperty("questionsQuantity"));
            this.gameLiveTime = Integer.parseInt(prop.getProperty("gameLiveTime"));

        } catch (Exception e) {
            //System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
    }

    public int getQuestionsQuantity() {
        return questionsQuantity;
    }

    public int getGameLiveTime() {
        return gameLiveTime;
    }

}
