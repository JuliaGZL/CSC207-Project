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


public class ProcessArrayBot {
    static final String [] GREATING_LIST = {"hi", "hello", "你好"};

    public static void main(String[] args) {

        DiscordClient client = DiscordClient.create(
                "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto");

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event ->
                            Mono.fromRunnable(() -> {
                                final User self = event.getSelf();
                                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                            }))
                    .then();

            // MessageCreateEvent example
            Mono<Void> handleGreetingCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                Optional<Member> member = event.getMember();

                String memberName = member.get().getUsername();
                String content = message.getContent();
                Boolean isGreating = false;
                for (String msg: GREATING_LIST){
                    if (content.equalsIgnoreCase(msg)) {
                        isGreating = true;
                        break;
                    }
                }

                if (isGreating) {
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage(content + " " + memberName));
                }

                return Mono.empty();
            }).then();

            // combine them!
            return printOnLogin.and(handleGreetingCommand);
        });

        login.block();
    }
}
