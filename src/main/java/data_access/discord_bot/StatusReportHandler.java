package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;


/**
 * Event handler for generating a status report in response to a command.
 *
 * This handler listens for the "report status" command and responds with
 * a message indicating everything is fine, along with the member's name.
 */
public class StatusReportHandler extends MessageHandler {


    /**
     * Constructor for StatusReportHandler.
     *
     * @param client the Discord client used to interact with the Discord API
     * @param gateway the gateway client for connecting to the Discord server
     */
    public StatusReportHandler(final DiscordClient client,
                               final GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            // Retrieve the member's name and message content from the event
            String memberName = getMemberName(event);
            String content = getContent(event);

            // Check if the message should trigger the status report
            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(memberName));
            }

            return EventHandler.defaultReturn();
        });
    }

    /**
     * Generates the status report message for the member.
     *
     * @param memberName the name of the member requesting the status
     * @return a message indicating that everything is fine, addressed to
     *         the member
     */
    public static String newMessage(final String memberName) {
        return "Dear " + memberName + " , everything is fine";
    }

    /**
     * Determines if the given message content should trigger the status report.
     *
     * @param message the message content
     * @return true if the message matches "report status"; false otherwise
     */
    public static boolean invokeMessage(final String message) {
        return message.equalsIgnoreCase("report status");
    }
}
