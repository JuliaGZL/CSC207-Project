package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

public class QuitBotEvent extends MessageInteractor {
    public QuitBotEvent(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                System.out.println("Invoke Leaving.");
                return sendMessage(event, newMessage(content, memberName)).and(gateway.logout());
            }

            return SubEventCreator.defaultReturn();
        });
    }

    public static String newMessage(String content, String memberName) {
        return "See ya'll! Especially you,  " + memberName;
    }

    public static boolean invokeMessage(String message) {
        return message.equals("!vc leave");
    }
}
