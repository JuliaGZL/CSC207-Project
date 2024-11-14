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

    public EventHandlerFactory(String token, Mono<Void> event) {
        this.client = DiscordClient.create(token);
        this.event = event;
    }

    public void run(){
        this.event.block();
    }

    public EventHandlerFactory union(EventHandlerFactory other) {
        return new EventHandlerFactory(client, this.event.and(other.event));
    }

    public EventHandlerFactory union(SubEventCreator other) {
        return new EventHandlerFactory(client, this.event.and(other.getExecutableEvent()));
    }

    public Mono<Void> getEvent() {
        return event;
    }
}
