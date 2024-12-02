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
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

/**
 * Handles the login event for the Discord bot.
 */
public class LoginHandler extends EventHandler<ReadyEvent> {

  /**
   * Constructs a new LoginHandler.
   *
   * @param client  the Discord client
   * @param gateway the gateway Discord client
   */
  public LoginHandler(DiscordClient client, GatewayDiscordClient gateway) {
    super(client, gateway, ReadyEvent.class, event -> Mono.fromRunnable(() -> {
      final User self = event.getSelf();
      System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
    }));
  }
}
