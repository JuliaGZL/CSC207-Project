package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.Arrays;
import java.util.List;

public class GreetingHandler extends MessageHandler {
    static final List<String> GREETING_LIST = Arrays.asList("hello", "hi", "hey", "你好");

    public GreetingHandler(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(content, memberName));
            }

            return EventHandler.defaultReturn();
        });
    }

    public static String newMessage(String content, String memberName) {
        return content + " " + memberName;
    }

    public static boolean invokeMessage(String message) {
        return GREETING_LIST.stream().anyMatch(message::equalsIgnoreCase);
    }
}
