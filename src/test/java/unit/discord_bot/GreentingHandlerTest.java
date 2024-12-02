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

import dataaccess.discordbot.GreetingHandler;

import static org.junit.Assert.*;

public class GreentingHandlerTest {
  @Test
  public void testGreetingMessage() {
    String greet = GreetingHandler.newMessage("HeLLO", "bill");
    assertEquals("HeLLO bill", greet);
  }

  @Test
  public void testInvokeGreetingFalse() {
    String msg = "Hello World!";
    assertFalse(GreetingHandler.invokeMessage(msg));
  }

  @Test
  public void testInvokeGreetingTrue() {
    String msg = "HelLo";
    assertTrue(GreetingHandler.invokeMessage(msg));
  }
}
