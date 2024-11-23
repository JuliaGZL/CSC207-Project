package usecase.remove_tile.api_usecase;

import discord4j.core.DiscordClient;
import reactor.core.publisher.Mono;

public class ParallelEvent {
    DiscordClient client;
    Mono<Void> event;

    public ParallelEvent(DiscordClient client, Mono<Void> event) {
        this.client = client;
        this.event = event;
    }

    public ParallelEvent union(ParallelEvent other) {
        return new ParallelEvent(client, this.event.and(other.event));
    }

    public ParallelEvent union(SubEventCreator other) {
        return new ParallelEvent(client, this.event.and(other.getExecutableEvent()));
    }

    public Mono<Void> getEvent() {
        return event;
    }
}
