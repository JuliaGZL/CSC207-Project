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
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * ChatBot is responsible for managing the connection to Discord and handling
 * events.
 */
public class ChatBot {
  String token;
  DiscordClient client;
  EventHandlerFactory handlerFactory;
  List<Class<? extends EventHandler<?>>> subEventsClasses = new ArrayList<>();
  Mono<Void> onConnect;

  /**
   * Constructs a ChatBot with the specified token.
   *
   * @param token the token used to authenticate with Discord
   */
  public ChatBot(String token) {
    this.token = token;
    this.client = DiscordClient.create(token);
    this.handlerFactory = new HandlerFactoryByClass();
  }

  /**
   * Adds an event type to be handled by the ChatBot.
   *
   * @param eventType the class of the event handler to be added
   */
  public void addEvent(Class<? extends EventHandler<?>> eventType) {
    subEventsClasses.add(eventType);
  }

  /**
   * Sets up the main service to handle events when connected to Discord.
   */
  public void setMainService() {
    this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
      // Collect all events as Mono<Void> and combine them
      Class<? extends EventHandler<?>> subEventType1 = subEventsClasses.remove(0);
      EventHandler<?> subEvent1 = handlerFactory.createEventHandler(subEventType1, client, gateway);
      if (subEventsClasses.size() == 0) {
        return subEvent1.getExecutableEvent();
      }
      ParallelEventCombiner parellel;

      EventHandler<?> subEvent2 = handlerFactory.createEventHandler(
          subEventsClasses.remove(0), client, gateway);
      parellel = subEvent1.union(subEvent2);

      for (int i = 0; i < subEventsClasses.size(); i++) {
        parellel = parellel.union(handlerFactory.createEventHandler(
            subEventsClasses.get(i), client, gateway));
      }
      return parellel.getEvent();
    });
  }

  /**
   * Runs the ChatBot, blocking until the connection is closed.
   */
  public void run() {
    setMainService();
    this.onConnect.block();
  }

  /**
   * Returns the list of event handler classes that have been added.
   *
   * @return the list of event handler classes
   */
  public List<Class<? extends EventHandler<?>>> getSubEventsClasses() {
    return subEventsClasses;
  }
}
