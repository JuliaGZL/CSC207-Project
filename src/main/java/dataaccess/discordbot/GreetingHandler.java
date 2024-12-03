package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Handles greeting messages in the Discord bot.
 *
 * This handler processes incoming messages and
 * checks if they match common greetings.
 *
 * If a greeting is detected, the bot responds
 * with a personalized greeting message.
 */
public class GreetingHandler extends MessageHandler {

    /**
     * List of recognized greeting phrases.
     */
    static final List<String> GREETING_LIST = Arrays.asList(
            "hello", "hi", "hey", "你好", "こんにちは");

    /**
     * Constructs a GreetingHandler that handles greeting messages.
     *
     * @param client the Discord client used to connect to Discord
     * @param gateway the GatewayDiscordClient used to
     *                interact with the Discord gateway
     */
    public GreetingHandler(final DiscordClient client,
                           final GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(content, memberName));
            }

            return EventHandler.defaultReturn();
        });
    }

    /**
     * Creates a new message that includes the original
     * greeting and the member's name.
     *
     * @param content the content of the message (greeting)
     * @param memberName the name of the member who sent the message
     * @return the new message combining the greeting and member's name
     */
    public static String newMessage(final String content,
                                    final String memberName) {
        return content + " " + memberName;
    }

    /**
     * Checks if the provided message matches any of the recognized greetings.
     *
     * @param message the message content to check
     * @return true if the message matches a greeting, false otherwise
     */
    public static boolean invokeMessage(final String message) {
        return GREETING_LIST.stream().anyMatch(message::equalsIgnoreCase);
    }
}
