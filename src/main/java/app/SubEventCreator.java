package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class SubEventCreator<E extends discord4j.core.event.domain.Event> {
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

}
