package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

public class TestSubEventCreatorsCombined {

    public static void main(String[] args) {

        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        DiscordClient client = DiscordClient.create(token);

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            LoginEvent loginReport = new LoginEvent(client, gateway);
            Mono<Void> printOnLogin = loginReport.getExecutableEvent();

            // MessageCreateEvent example
            GreetingEvent greetingResponse = new GreetingEvent(client, gateway);
            Mono<Void> handleGreetingCommand = greetingResponse.getExecutableEvent();

            // MessageCreateEvent example
            StatusEvent statusReporter = new StatusEvent(client, gateway);
            Mono<Void> handleTestingCommand = statusReporter.getExecutableEvent();

            // combine them!
            return printOnLogin.and(handleGreetingCommand).and(handleTestingCommand);
        });

        login.block();
    }
}
