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

import dataaccess.discordbot.ChatBot;
import dataaccess.discordbot.GreetingHandler;
import dataaccess.discordbot.LoginHandler;
import dataaccess.discordbot.MahjongComboHandler;
import dataaccess.discordbot.QuitHandler;

import org.junit.Test;
import static org.junit.Assert.*;

public class addEventTest {
  @Test
  public void addEventListMatch() {
    String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

    ChatBot bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
    System.out.println(bot.getSubEventsClasses());
    String expected = "[class dataaccess.discordbot.LoginHandler, " +
        "class dataaccess.discordbot.GreetingHandler, " +
        "class dataaccess.discordbot.MahjongComboHandler, " +
        "class dataaccess.discordbot.QuitHandler]";
    assertEquals(expected, bot.getSubEventsClasses().toString());
  }
}
