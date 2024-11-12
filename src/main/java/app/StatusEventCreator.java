package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class StatusEventCreator extends SubEventCreator<MessageCreateEvent> {
    public StatusEventCreator(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            Message message = event.getMessage();
            Optional<Member> member = event.getMember();

            String memberName = member.map(Member::getUsername).orElse("Guest");
            String content = message.getContent();

            if (content.equalsIgnoreCase("report status")) {
                return message.getChannel()
                        .flatMap(channel -> channel.createMessage("Dear " + memberName + " , everything is fine").then());
            }

            return Mono.empty();
        });
    }
}
