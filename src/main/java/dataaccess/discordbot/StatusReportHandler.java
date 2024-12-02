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
 * Handles status report messages in a Discord bot.
 */
public class StatusReportHandler extends MessageHandler {

  /**
   * Constructs a StatusReportHandler.
   *
   * @param client  the Discord client
   * @param gateway the Discord gateway client
   */
  public StatusReportHandler(DiscordClient client, GatewayDiscordClient gateway) {
    super(client, gateway, MessageCreateEvent.class, event -> {
      String memberName = getMemberName(event);
      String content = getContent(event);

      if (invokeMessage(content)) {
        return sendMessage(event, newMessage(memberName));
      }

      return EventHandler.defaultReturn();
    });
  }

  /**
   * Generates a new message for the status report.
   *
   * @param memberName the name of the member
   * @return a status report message
   */
  public static String newMessage(String memberName) {
    return "Dear " + memberName + " , everything is fine";
  }

  /**
   * Checks if the message invokes a status report.
   *
   * @param message the message content
   * @return true if the message is "report status", false otherwise
   */
  public static boolean invokeMessage(String message) {
    return message.equalsIgnoreCase("report status");
  }
}
