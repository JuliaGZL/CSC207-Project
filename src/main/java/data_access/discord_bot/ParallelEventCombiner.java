package data_access.discord_bot;

import discord4j.core.DiscordClient;
import reactor.core.publisher.Mono;

public class ParallelEventCombiner {
    DiscordClient client;
    Mono<Void> event;

    public ParallelEventCombiner(DiscordClient client, Mono<Void> event) {
        this.client = client;
        this.event = event;
    }

    public ParallelEventCombiner union(ParallelEventCombiner other) {
        return new ParallelEventCombiner(client, this.event.and(other.event));
    }

    public ParallelEventCombiner union(EventHandler other) {
        return new ParallelEventCombiner(client, this.event.and(other.getExecutableEvent()));
    }

    public Mono<Void> getEvent() {
        return event;
    }
}
