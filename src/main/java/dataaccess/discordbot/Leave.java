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

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.voice.VoiceConnection;
import reactor.core.publisher.Mono;

/**
 * Handles the command to leave a voice channel in Discord.
 */
public class Leave {

  private final GatewayDiscordClient client;

  /**
   * Constructs a Leave command handler.
   *
   * @param client the Discord client
   */
  public Leave(GatewayDiscordClient client) {
    this.client = client;
  }

  /**
   * Handles the message creation event to leave a voice channel.
   *
   * @param event the message creation event
   * @return a Mono that completes when the bot has left the voice channel
   */
  public Mono<Void> onMessageCreate(MessageCreateEvent event) {
    if (event.getMessage().getContent().equals("!vc leave")) {
      return Mono.justOrEmpty(event.getMember())
          .flatMap(Member::getVoiceState)
          .flatMap(vs -> client.getVoiceConnectionRegistry()
              .getVoiceConnection(vs.getGuildId())
              .doOnSuccess(vc -> {
                if (vc == null) {
                  System.out.println("No voice connection to leave!");
                }
              }))
          .flatMap(VoiceConnection::disconnect);
    }
    return Mono.empty();
  }
}