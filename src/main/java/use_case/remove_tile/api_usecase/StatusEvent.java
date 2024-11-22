package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;


public class StatusEvent extends MessageInteractor {

    public StatusEvent(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(memberName));
            }

            return SubEventCreator.defaultReturn();
        });
    }

    public static String newMessage(String memberName) {
        return "Dear " + memberName + " , everything is fine";
    }

    public static boolean invokeMessage(String message) {
        return message.equalsIgnoreCase("report status");
    }
}
