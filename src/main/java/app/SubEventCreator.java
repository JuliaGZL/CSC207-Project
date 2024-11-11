package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class SubEventCreator<E extends discord4j.core.event.domain.Event> {
    DiscordClient client;
    Mono<Void> event;
    GatewayDiscordClient gateway;
    Function<E, Mono<Void>> eventMapper;

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Mono<Void> event){

        this.gateway = gateway;
    }

    public SubEventCreator(DiscordClient client, GatewayDiscordClient gateway, Function<E, Mono<Void>> eventMapper) {

    }

    public Mono<Void> mapperToEvent(GatewayDiscordClient gateway, Class<E> eventClass, Function<E, Publisher<Void>> eventMapper){
        return gateway.on(eventClass, eventMapper).then();
    }

}
