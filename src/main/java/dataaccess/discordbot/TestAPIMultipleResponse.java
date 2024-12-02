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
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.voice.VoiceConnection;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

/**
 * This class demonstrates a simple Discord bot using the Discord4J library.
 * It handles multiple types of responses to user messages.
 */
public class TestAPIMultipleResponse {
  static final List<String> GREETING_LIST = Arrays.asList("hello", "hi", "hey", "你好");

  /**
   * The main method to start the Discord bot.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {

    DiscordClient client = DiscordClient.create(
        "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto");

    Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
      // Handles the ReadyEvent to print a message when the bot logs in.
      Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event -> Mono.fromRunnable(() -> {
        final User self = event.getSelf();
        System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
      }))
          .then();

      // Handles the MessageCreateEvent to respond to greeting messages.
      Mono<Void> handleGreetingCommand = gateway.on(MessageCreateEvent.class, event -> {
        Message message = event.getMessage();
        Optional<Member> member = event.getMember();

        String memberName = member.map(Member::getUsername).orElse("Guest");
        String content = message.getContent();
        boolean isGreeting = GREETING_LIST.stream().anyMatch(content::equalsIgnoreCase);

        if (isGreeting) {
          return message.getChannel()
              .flatMap(channel -> channel.createMessage(content + " " + memberName));
        }

        return Mono.empty();
      }).then();

      // Handles the MessageCreateEvent to respond to "report status" messages.
      Mono<Void> handleTestingCommand = gateway.on(MessageCreateEvent.class, event -> {
        Message message = event.getMessage();
        Optional<Member> member = event.getMember();

        String memberName = member.map(Member::getUsername).orElse("Guest");
        String content = message.getContent();

        if (content.equalsIgnoreCase("report status")) {
          assert false;
          return message.getChannel()
              .flatMap(channel -> channel.createMessage(
                  "Dear " + memberName + " , everything is fine"));
        }

        return Mono.empty();
      }).then();

      // Handles the MessageCreateEvent to respond to "!vc leave" messages
      // and disconnect from voice.
      Mono<Void> handleQuitCmmand = gateway.on(MessageCreateEvent.class, event -> {
        if (event.getMessage().getContent().equals("!vc leave")) {
          System.out.println("no leave");
          gateway.logout().block();
          return Mono.justOrEmpty(event.getMember())
              .flatMap(Member::getVoiceState)
              .flatMap(vs -> gateway.getVoiceConnectionRegistry()
                  .getVoiceConnection(vs.getGuildId())
                  .doOnSuccess(vc -> {
                    if (vc == null) {
                      System.out.println("No voice connection to leave!");
                    }
                  }))
              .flatMap(VoiceConnection::disconnect);
        }
        return Mono.empty();
      }).then();

      // combine them!
      return printOnLogin.and(
          handleGreetingCommand).and(handleTestingCommand).and(handleQuitCmmand);
    });

    login.block();
  }
}
