package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;


/**
 * Event handler for handling the 'quit' action in the bot.
 *
 * This handler listens for specific messages (e.g., "!vc leave", "push",
 * "send") and performs corresponding actions such as sending a farewell
 * message and logging out the bot.
 */
public class QuitHandler extends MessageHandler {

    /**
     * Constructor for QuitHandler.
     *
     * @param client the Discord client used to interact with the Discord API
     * @param gateway the gateway client for connecting to the Discord server
     */
    public QuitHandler(final DiscordClient client,
                       final GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {

            // Retrieve member's name and message content from the event
            String memberName = getMemberName(event);
            String content = getContent(event);

            // Check if the message should trigger the quit action
            if (invokeMessage(content)) {
                // Log the quit action and send a farewell message
                System.out.println("Invoke Leaving.");
                return sendMessage(event,
                        newMessage(content, memberName)
                ).and(gateway.logout());
            }

            return EventHandler.defaultReturn();
        });
    }


    /**
     * Generates a message based on the content of the command.
     *
     * @param content the content of the message received
     * @param memberName the name of the member who sent the message
     * @return a response message based on the input content
     */
    public static String newMessage(final String content,
                                    final String memberName) {
        if (content.equalsIgnoreCase(
                "push") || content.equalsIgnoreCase(
                "send")) {
            return "Your combination sent!";
        }
        return "See ya'll! Especially you, " + memberName;
    }

    /**
     * Determines if the given message content should trigger the quit action.
     *
     * @param message the message content
     * @return true if the message matches a valid quit action; false otherwise
     */
    public static boolean invokeMessage(final String message) {
        if (message.equals("!vc leave")) {
            return true;
        }
        if (message.equalsIgnoreCase("push")) {
            return true;
        }
        return message.equalsIgnoreCase("send");
    }
}
