package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import discord4j.voice.VoiceConnection;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestAPIMultipleResponse {
    static final List<String> GREETING_LIST = Arrays.asList("hello", "hi", "hey", "你好");

    public static void main(String[] args) {

        DiscordClient client = DiscordClient.create(
                "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto");

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> {
            // ReadyEvent example
            Mono<Void> printOnLogin = gateway.on(ReadyEvent.class, event -> Mono.fromRunnable(() -> {
                final User self = event.getSelf();
                System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
            }))
                    .then();

            // MessageCreateEvent example
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

            // MessageCreateEvent example
            Mono<Void> handleTestingCommand = gateway.on(MessageCreateEvent.class, event -> {
                Message message = event.getMessage();
                Optional<Member> member = event.getMember();

                String memberName = member.map(Member::getUsername).orElse("Guest");
                String content = message.getContent();

                if (content.equalsIgnoreCase("report status")) {
                    assert false;
                    return message.getChannel()
                            .flatMap(channel -> channel.createMessage("Dear " + memberName + " , everything is fine"));
                }

                return Mono.empty();
            }).then();

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
            return printOnLogin.and(handleGreetingCommand).and(handleTestingCommand).and(handleQuitCmmand);
        });

        login.block();
    }
}
