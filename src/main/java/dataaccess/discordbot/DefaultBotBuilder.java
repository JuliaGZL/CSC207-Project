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

package dataaccess.discordbot;

/**
 * DefaultBotBuilder is responsible for building and configuring a ChatBot
 * instance.
 */
public class DefaultBotBuilder implements BotBuilder {
  final String token;
  private final ChatBot bot;

  /**
   * Constructs a DefaultBotBuilder with a default token.
   */
  public DefaultBotBuilder() {
    this.token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";
    bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(StatusReportHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
  }

  /**
   * Constructs a DefaultBotBuilder with a specified token.
   *
   * @param token the token to be used by the ChatBot
   */
  public DefaultBotBuilder(String token) {
    this.token = token;
    bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(StatusReportHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
  }

  /**
   * Returns the configured ChatBot instance.
   *
   * @return the ChatBot instance
   */
  public ChatBot getBot() {
    return bot;
  }

  /**
   * Activates the ChatBot to start running.
   */
  public void activateBot() {
    bot.run();
  }

  /**
   * Retrieves a message from the MessageHolder.
   *
   * @return the message from the MessageHolder
   */
  public String getMessage() {
    return MessageHolder.getMessage();
  }
}
