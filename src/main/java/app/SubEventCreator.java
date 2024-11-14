package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import discord4j.core.event.domain.Event;

import java.util.function.Function;

public class SubEventCreator<E extends Event> {
    DiscordClient client;
    Mono<Void> event;
    GatewayDiscordClient gateway;
    Function<E, Publisher<Void>> eventMapper;

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Mono<Void> event){
        this.client = client;
        this.event = event;
        this.gateway = gateway;
    }

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Class<E> eventClass,
                           Function<E, Publisher<Void>> eventMapper) {
        this.client = client;
        this.gateway = gateway;
        this.eventMapper = eventMapper;
        this.event = mapperToEvent(gateway, eventClass, eventMapper);
    }

    public Mono<Void> mapperToEvent(GatewayDiscordClient gateway, Class<E> eventClass,
                                    Function<E, Publisher<Void>> eventMapper){
        return gateway.on(eventClass, eventMapper).then();
    }

    public Mono<Void> getEvent() {
        return event;
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
}
