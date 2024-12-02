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

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * Handles the quit command for the Discord bot.
 */
public class QuitHandler extends MessageHandler {
  /**
   * Constructs a QuitHandler.
   *
   * @param client  the Discord client
   * @param gateway the GatewayDiscordClient
   */
  public QuitHandler(DiscordClient client, GatewayDiscordClient gateway) {
    super(client, gateway, MessageCreateEvent.class, event -> {
      String memberName = getMemberName(event);
      String content = getContent(event);

      if (invokeMessage(content)) {
        System.out.println("Invoke Leaving.");
        return sendMessage(event, newMessage(content, memberName)).and(gateway.logout());
      }

      return EventHandler.defaultReturn();
    });
  }

  /**
   * Generates a new message based on the content and member name.
   *
   * @param content    the content of the message
   * @param memberName the name of the member
   * @return the generated message
   */
  public static String newMessage(String content, String memberName) {
    if (content.equalsIgnoreCase("push") || content.equalsIgnoreCase("send")) {
      return "Your combination sent!";
    }
    return "See ya'll! Especially you, " + memberName;
  }

  /**
   * Determines if the message should invoke the quit command.
   *
   * @param message the message to check
   * @return true if the message should invoke the quit command, false otherwise
   */
  public static boolean invokeMessage(String message) {
    if (message.equals("!vc leave")) {
      return true;
    }
    if (message.equalsIgnoreCase("push")) {
      return true;
    }
    return message.equalsIgnoreCase("send");
  }
}
