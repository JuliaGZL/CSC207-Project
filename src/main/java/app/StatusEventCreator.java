package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;


public class StatusEventCreator extends SubEventCreator<MessageCreateEvent> {

    public StatusEventCreator(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = SubEventCreator.getMemberName(event);
            String content = SubEventCreator.getContent(event);

            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(memberName));
            }

            return Mono.empty();
        });
    }

    public static String newMessage(String memberName) {
        return "Dear " + memberName + " , everything is fine";
    }

    public static boolean invokeMessage(String message) {
        return message.equalsIgnoreCase("report status");
    }


}
