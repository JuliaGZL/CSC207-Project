package app;

import discord4j.core.DiscordClient;
import reactor.core.publisher.Mono;

public class EventHandlerFactory {
    DiscordClient client;
    Mono<Void> event;

    public EventHandlerFactory(DiscordClient client, Mono<Void> event) {
        this.client = client;
        this.event = event;
    }
}
