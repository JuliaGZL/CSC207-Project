package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Abstract handler for processing Discord message events.
 *
 * This class extends {@link EventHandler} and is
 * specifically designed to handle {@link MessageCreateEvent} events.
 * It provides utility methods to interact with messages,
 * retrieve content, member names, and send messages back to the channel.
 */
public abstract class MessageHandler extends EventHandler<MessageCreateEvent> {

    /**
     * Constructs a MessageHandler to handle message creation events.
     *
     * This constructor initializes the event handler
     * with a given client, gateway, event class,
     * and event mapper function to map the event to a corresponding publisher.
     *
     * @param client the Discord client used to interact
     *               with Discord
     * @param gateway the GatewayDiscordClient used to interact
     *                with the Discord gateway
     * @param eventClass the class type for the {@link MessageCreateEvent}
     * @param eventMapper a function that maps a {@link MessageCreateEvent}
     *                    to a {@link Publisher} of {@link Void}
     */
    public MessageHandler(final DiscordClient client,
                          final GatewayDiscordClient gateway,
                          final Class<MessageCreateEvent> eventClass,
                          final Function<MessageCreateEvent, Publisher<Void>>
                                  eventMapper) {
        super(client, gateway, eventClass, eventMapper);
    }

    /**
     * Retrieves the content of the message from the provided
     * {@link MessageCreateEvent}.
     *
     * @param fromEvent the event containing the message
     * @return the content of the message as a {@link String}
     */
    public static String getContent(final MessageCreateEvent fromEvent) {
        return fromEvent.getMessage().getContent();
    }

    /**
     * Retrieves the username of the member who sent the
     * message from the provided {@link MessageCreateEvent}.
     * If the member is not present, it returns "Guest" as the default.
     *
     * @param fromEvent the event containing the member information
     * @return the username of the member, or "Guest" if the member is absent
     */
    public static String getMemberName(final MessageCreateEvent fromEvent) {
        return fromEvent.getMember().map(Member::getUsername).orElse("Guest");
    }

    /**
     * Sends a message back to the channel where the
     * {@link MessageCreateEvent} was triggered.
     *
     * @param fromEvent the event containing the message
     * @param text the text to send back to the channel
     * @return a {@link Mono} indicating the result of sending the message
     */
    public static Mono<Void> sendMessage(
            final MessageCreateEvent fromEvent,
            final String text) {
        Message message = fromEvent.getMessage();
        return message.getChannel()
                .flatMap(channel -> channel.createMessage(text).then());
    }

    /**
     * Placeholder method for generating a new message.
     * This method is not supported and throws an exception if invoked.
     *
     * @param input the input message to generate a new message
     * @throws UnsupportedOperationException always thrown
     */
    public static String newMessage(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Placeholder method for determining if a message should be invoked.
     * This method is not supported and throws an exception if invoked.
     *
     * @param message the message content to evaluate
     * @throws UnsupportedOperationException always thrown
     */
    public static boolean invokeMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
