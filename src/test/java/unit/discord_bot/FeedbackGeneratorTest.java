package unit.discord_bot;

import data_access.discord_bot.FeedbackGenerator;
import data_access.discord_bot.QuitHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.*;

public class FeedbackGeneratorTest {
    @Test
    public void testFeedbackGeneratorAllm() {
        String combo = "1 1 1 2 2 2 3 3 3 4 4 4 5 5 m";
        List<String> feedbackList = FeedbackGenerator.getResultString(combo);
        String feedback = String.join("\n", feedbackList);
        assertEquals(feedback, "Double Yakuman\n" +
                "Suuankou Tanki: Double Yakuman\n" +
                "Fan: 26\n" +
                "Fu: 20\n" +
                "Score: 64,000 Points");
    }

    @Test
    public void testFeedbackGeneratorMix() {
        String combo = "5 6 7 m 5 6 7 p 2 2 5 6 7 s 4 4 4 z";
        List<String> feedbackList = FeedbackGenerator.getResultString(combo);
        String feedback = String.join("\n", feedbackList);
        assertEquals(feedback, "\nSanshoku doujun: 2 Fan\n" +
                "Fan: 2\n" +
                "Fu: 40\n" +
                "Score: 2,600 Points");
    }

    @Test
    public void testFeedbackGeneratorError() {
        String combo = "5 6 7 m 5 6 7 p 2 2 5 6 7 s 4 4 4 10 z";
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    List<String> feedbackList = FeedbackGenerator.getResultString(combo);
                }
        );
    }
}
