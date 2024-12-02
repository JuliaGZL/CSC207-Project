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

import org.junit.Test;

import dataaccess.discordbot.QuitHandler;

import static org.junit.Assert.*;

public class QuitHandlerTest {
  @Test
  public void testQuitMessage() {
    String exampleMessage = QuitHandler.newMessage("the content", "paul");
    assertEquals("See ya'll! Especially you, paul", exampleMessage);
  }

  @Test
  public void testQuitInvokeConditionTrue() {
    boolean invoke = QuitHandler.invokeMessage("!vc leave");
    assertTrue(invoke);
  }

  @Test
  public void testQuitInvokeConditionFalse() {
    boolean invoke = QuitHandler.invokeMessage("leave");
    assertFalse(invoke);
  }
}
