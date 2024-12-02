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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dataaccess.discordbot.FeedbackGenerator;
import dataaccess.discordbot.MahjongComboHandler;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MahjongComboHandlerTest {

  @Test
  public void testMahjong() {
    String userInput = "Mahjong 1 1 1 2 2 2 3 3 3 4 4 4 5 5 m";
    String botOutput = "Double Yakuman\n" +
        "Suuankou Tanki: Double Yakuman\n" +
        "Fan: 26\n" +
        "Fu: 20\n" +
        "Score: 64,000 Points";
    String expected = MahjongComboHandler.getResultString(userInput);
    assertEquals(expected, botOutput);
  }

  @Test
  public void testMahjongError() {
    String combo = "Mahjong 5 6 7 m 5 6 7 p 2 2 5 6 7 s 4 4 4 10 z";
    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          List<String> feedbackList = FeedbackGenerator.getResultString(combo);
        });
  }

  @Test
  public void testIsLegalIdentifierTrue() {
    String c = "m";
    assertTrue(MahjongComboHandler.isLegalIdentifier(c));
  }

  @Test
  public void testIsLegalIdentifierFalseMultiple() {
    String c = "mcc";
    assertFalse(MahjongComboHandler.isLegalIdentifier(c));
  }

  @Test
  public void testIsLegalIdentifierFalseDigit() {
    String c = "1";
    assertFalse(MahjongComboHandler.isLegalIdentifier(c));
  }

  @Test
  public void testInvoke() {
    String c = "Mahjong ...";
    assertTrue(MahjongComboHandler.invokeMessage(c));
  }

  @Test
  public void testProcessMahjongInput() {
    Map<Character, List<Integer>> processed = MahjongComboHandler.processMahjongInput(
        "Mahjong 2 1 m 1 2 s 1 2 z 3 m");
    assertEquals("{s=[1, 2], z=[1, 2], m=[1, 2, 3]}", processed.toString());
  }
}
