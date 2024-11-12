package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GeetingEventCreator extends SubEventCreator<MessageCreateEvent> {
    static final List<String> GREETING_LIST = Arrays.asList("hello", "hi", "hey", "你好");

    public GeetingEventCreator(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            Message message = event.getMessage();
            Optional<Member> member = event.getMember();

            String memberName = member.map(Member::getUsername).orElse("Guest");
            String content = message.getContent();
            boolean isGreeting = GREETING_LIST.stream().anyMatch(content::equalsIgnoreCase);

            if (isGreeting) {
                return message.getChannel()
                        .flatMap(channel -> channel.createMessage(content + " " + memberName).then());
            }

            return Mono.empty();
        });
    }
}
