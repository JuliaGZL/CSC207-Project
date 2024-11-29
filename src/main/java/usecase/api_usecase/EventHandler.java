package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import discord4j.core.event.domain.Event;

import java.util.function.Function;

public class SubEventCreator<E extends Event> {
    DiscordClient client;
    Mono<Void> eventVoid;
    GatewayDiscordClient gateway;
    Function<E, Publisher<Void>> eventMapper;

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Mono<Void> eventVoid){
        this.client = client;
        this.eventVoid = eventVoid;
        this.gateway = gateway;
    }

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Class<E> eventClass,
                           Function<E, Publisher<Void>> eventMapper) {
        this.client = client;
        this.gateway = gateway;
        this.eventMapper = eventMapper;
        this.eventVoid = mapperToEvent(gateway, eventClass, eventMapper);
    }

    public Mono<Void> mapperToEvent(GatewayDiscordClient gateway, Class<E> eventClass,
                                    Function<E, Publisher<Void>> eventMapper){
        return gateway.on(eventClass, eventMapper).then();
    }

    public Mono<Void> getExecutableEvent() {
        return eventVoid;
    }

    public static Mono<Void> defaultReturn() {
        return Mono.empty();
    }

    public ParallelEventCombiner union(SubEventCreator<? extends Event> other) {
        return new ParallelEventCombiner(this.client, this.getExecutableEvent().and(other.getExecutableEvent()));
    }
}
