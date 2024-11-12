package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class TestLoginEventCreator {

    public static void main(String[] args) {

        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        DiscordClient client = DiscordClient.create(token);

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            LoginEventCreator loginEvent = new LoginEventCreator(client, gateway);
            Mono<Void> printOnLogin = loginEvent.getEvent();

            // MessageCreateEvent example
            GeetingEventCreator greetingEvent = new GeetingEventCreator(client, gateway);
            Mono<Void> handleGreetingCommand = greetingEvent.getEvent();

            // MessageCreateEvent example
            StatusEventCreator statusReporter = new StatusEventCreator(client, gateway);
            Mono<Void> handleTestingCommand = statusReporter.getEvent();

            // combine them!
            return printOnLogin.and(handleGreetingCommand).and(handleTestingCommand);
        });

        login.block();
    }
}
