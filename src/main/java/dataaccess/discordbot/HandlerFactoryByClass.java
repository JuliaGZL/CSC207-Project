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

/**
 * Factory class to create event handlers based on the provided class type.
 */
public class HandlerFactoryByClass implements EventHandlerFactory {

  /**
   * Creates an event handler based on the provided event type.
   *
   * @param eventType the class type of the event handler to create
   * @param client    the Discord client
   * @param gateway   the Gateway Discord client
   * @return the created event handler
   * @throws IllegalArgumentException if the event type is unknown
   */
  @Override
  public EventHandler createEventHandler(Class<? extends EventHandler<?>> eventType,
      DiscordClient client, GatewayDiscordClient gateway) {
    if (eventType.equals(LoginHandler.class)) {
      System.out.println("Login Event Created");
      return new LoginHandler(client, gateway);
    }
    if (eventType.equals(MahjongComboHandler.class)) {
      System.out.println("Mahjong Event Created");
      return new MahjongComboHandler(client, gateway);
    }
    if (eventType.equals(GreetingHandler.class)) {
      System.out.println("Greating Event Created");
      return new GreetingHandler(client, gateway);
    }
    if (eventType.equals(StatusReportHandler.class)) {
      System.out.println("Status Event Created");
      return new StatusReportHandler(client, gateway);
    }
    if (eventType.equals(QuitHandler.class)) {
      System.out.println("Quit Bot Event Created");
      return new QuitHandler(client, gateway);
    }
    throw new IllegalArgumentException("Unknown event type: " + eventType);
  }

}
