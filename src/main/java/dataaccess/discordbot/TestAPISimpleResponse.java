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
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

/*
* https://docs.discord4j.com/basic-bot-tutorial
* */

public class TestAPISimpleResponse {
  public static void main(String[] args) {
    DiscordClient client = DiscordClient.create(
        "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto");

    Mono<Void> login = client
        .withGateway(
            (GatewayDiscordClient gateway) -> gateway.on(MessageCreateEvent.class, event -> {
              Message message = event.getMessage();

              if (message.getContent().equalsIgnoreCase("!ping")) {
                return message.getChannel()
                    .flatMap(channel -> channel.createMessage("pong!"));
              }

              return Mono.empty();
            }));

    login.block();
  }
}
