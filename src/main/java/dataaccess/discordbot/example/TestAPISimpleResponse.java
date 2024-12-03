package dataaccess.discordbot.example;
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

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(MessageCreateEvent.class, event -> {
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
