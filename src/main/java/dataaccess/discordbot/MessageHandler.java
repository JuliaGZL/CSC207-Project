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
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

/**
 * Abstract class for handling Discord message events.
 */
public abstract class MessageHandler extends EventHandler<MessageCreateEvent> {

  /**
   * Constructs a MessageHandler.
   *
   * @param client      the Discord client
   * @param gateway     the GatewayDiscordClient
   * @param eventClass  the class of the event to handle
   * @param eventMapper the function to map the event to a Publisher
   */
  public MessageHandler(
      DiscordClient client, GatewayDiscordClient gateway, Class<MessageCreateEvent> eventClass,
      Function<MessageCreateEvent, Publisher<Void>> eventMapper) {
    super(client, gateway, eventClass, eventMapper);
  }

  /**
   * Gets the content of the message from the event.
   *
   * @param fromEvent the message create event
   * @return the content of the message
   */
  public static String getContent(MessageCreateEvent fromEvent) {
    return fromEvent.getMessage().getContent();
  }

  /**
   * Gets the username of the member who sent the message.
   *
   * @param fromEvent the message create event
   * @return the username of the member, or "Guest" if not available
   */
  public static String getMemberName(MessageCreateEvent fromEvent) {
    return fromEvent.getMember().map(Member::getUsername).orElse("Guest");
  }

  /**
   * Sends a message to the same channel from which the event originated.
   *
   * @param fromEvent the message create event
   * @param text      the text to send
   * @return a Mono that completes when the message is sent
   */
  public static Mono<Void> sendMessage(MessageCreateEvent fromEvent, String text) {
    Message message = fromEvent.getMessage();
    return message.getChannel()
        .flatMap(channel -> channel.createMessage(text).then());
  }

  /**
   * Creates a new message based on the input.
   *
   * @param input the input text
   * @return the new message
   * @throws UnsupportedOperationException if the method is not supported
   */
  public static String newMessage(String input) {
    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * Invokes a message based on the input.
   *
   * @param message the input message
   * @return true if the message was successfully invoked, false otherwise
   * @throws UnsupportedOperationException if the method is not supported
   */
  public static boolean invokeMessage(String message) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
