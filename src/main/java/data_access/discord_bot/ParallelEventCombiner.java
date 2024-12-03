package data_access.discord_bot;

import discord4j.core.DiscordClient;
import reactor.core.publisher.Mono;

/**
 * A class to combine multiple events into one parallel execution.
 *
 * This class allows combining multiple events (represented by
 * {@link Mono<Void>}) into a single event, which can be executed concurrently.
 * The events are combined using the {@link #union(ParallelEventCombiner)}
 * and {@link #union(EventHandler)} methods.
 */
public class ParallelEventCombiner {

    /** The Discord client used to interact with the Discord API. */
    private final DiscordClient client;

    /** The event to be executed in parallel. */
    private final Mono<Void> event;

    /**
     * Constructs a {@link ParallelEventCombiner} with the specified Discord
     * client and event.
     *
     * @param client the Discord client used for event handling
     * @param event the event (a {@link Mono<Void>}) that represents the
     *              task to be executed
     */
    public ParallelEventCombiner(final DiscordClient client,
                                 final Mono<Void> event) {
        this.client = client;
        this.event = event;
    }

    /**
     * Combines this event with another {@link ParallelEventCombiner} to
     * be executed in parallel.
     *
     * This method creates a new {@link ParallelEventCombiner} that
     * represents the combination of the current event and the event
     * from the provided {@link ParallelEventCombiner}.
     *
     * @param other the other {@link ParallelEventCombiner} whose event
     *              is to be combined
     * @return a new {@link ParallelEventCombiner} with the combined event
     */
    public ParallelEventCombiner union(final ParallelEventCombiner other) {
        return new ParallelEventCombiner(client, this.event.and(other.event));
    }

    /**
     * Combines this event with the event from an {@link EventHandler} to
     * be executed in parallel.
     *
     * This method creates a new {@link ParallelEventCombiner} that
     * represents the combination of the current event and the event
     * from the provided {@link EventHandler}.
     *
     * @param other the {@link EventHandler} whose event is to be combined
     * @return a new {@link ParallelEventCombiner} with the combined event
     */
    public ParallelEventCombiner union(final EventHandler other) {
        return new ParallelEventCombiner(client,
                this.event.and(other.getExecutableEvent()));
    }

    /**
     * Returns the combined event represented by this
     * {@link ParallelEventCombiner}.
     *
     * @return the combined event as a {@link Mono<Void>}
     */
    public Mono<Void> getEvent() {
        return event;
    }
}
