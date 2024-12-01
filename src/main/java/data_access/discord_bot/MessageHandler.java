package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class MessageHandler extends EventHandler<MessageCreateEvent> {

    public MessageHandler(DiscordClient client, GatewayDiscordClient gateway, Class<MessageCreateEvent> eventClass,
                          Function<MessageCreateEvent, Publisher<Void>> eventMapper) {
        super(client, gateway, eventClass, eventMapper);
    }

    public static String getContent(MessageCreateEvent fromEvent) {
        return fromEvent.getMessage().getContent();
    }

    public static String getMemberName(MessageCreateEvent fromEvent) {
        return fromEvent.getMember().map(Member::getUsername).orElse("Guest");
    }

    public static Mono<Void> sendMessage(MessageCreateEvent fromEvent, String text) {
        Message message = fromEvent.getMessage();
        return message.getChannel()
                .flatMap(channel -> channel.createMessage(text).then());
    }

    public static String newMessage(String input) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static boolean invokeMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
