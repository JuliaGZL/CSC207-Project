/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package unit.discord_bot;

import static org.junit.Assert.assertEquals;

import dataaccess.discordbot.FeedbackGenerator;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

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
        });
  }
}
