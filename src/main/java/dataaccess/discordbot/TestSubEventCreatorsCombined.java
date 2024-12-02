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
import reactor.core.publisher.Mono;

public class TestSubEventCreatorsCombined {

  public static void main(String[] args) {

    String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

    DiscordClient client = DiscordClient.create(token);

    Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
      // ReadyEvent example
      LoginHandler loginReport = new LoginHandler(client, gateway);
      // Mono<Void> printOnLogin = loginReport.getExecutableEvent();

      // MessageCreateEvent example
      GreetingHandler greetingResponse = new GreetingHandler(client, gateway);
      // Mono<Void> handleGreetingCommand = greetingResponse.getExecutableEvent();

      // MessageCreateEvent example
      StatusReportHandler statusReporter = new StatusReportHandler(client, gateway);
      // Mono<Void> handleTestingCommand = statusReporter.getExecutableEvent();

      // combine them!
      return loginReport.union(greetingResponse).union(statusReporter).getEvent();
      // return printOnLogin.and(handleGreetingCommand).and(handleTestingCommand);
    });

    login.block();
  }
}
