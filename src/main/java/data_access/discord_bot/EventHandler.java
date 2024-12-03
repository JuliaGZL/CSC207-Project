package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import discord4j.core.event.domain.Event;

import java.util.function.Function;

/**
 * Abstract class for handling events in the Discord bot framework.
 *
 * @param <E> the type of event this handler processes
 */
public abstract class EventHandler<E extends Event> {

    /**
     * The Discord client used to connect to Discord.
     */
    private DiscordClient client;

    /**
     * The Mono<Void> representing the event's execution.
     */
    private Mono<Void> eventVoid;

    /**
     * The GatewayDiscordClient used to interact with the Discord gateway.
     */
    private GatewayDiscordClient gateway;

    /**
     * The function that maps the event to a publisher that returns a void.
     */
    private Function<E, Publisher<Void>> eventMapper;

    /**
     * Constructs an EventHandler instance with the
     * provided client, gateway, and event execution Mono.
     *
     * @param clientIpt the Discord client
     * @param gatewayIpt the GatewayDiscordClient
     *                for interacting with the Discord gateway
     * @param eventVoidIpt the Mono<Void> representing the event's execution
     */
    public EventHandler(final DiscordClient clientIpt,
                        final GatewayDiscordClient gatewayIpt,
                        final Mono<Void> eventVoidIpt) {
        this.client = clientIpt;
        this.eventVoid = eventVoidIpt;
        this.gateway = gatewayIpt;
    }

    /**
     * Constructs an EventHandler instance with the
     * provided client, gateway, event class, and event mapper.
     *
     * @param clientIpt the Discord client
     * @param gatewayIpt the GatewayDiscordClient for
     *                interacting with the Discord gateway
     * @param eventClass the class of the event to handle
     * @param eventMapperIpt the function that maps
     *                    the event to a publisher of void
     */
    public EventHandler(final DiscordClient clientIpt,
                        final GatewayDiscordClient gatewayIpt,
                        final Class<E> eventClass,
                        final Function<E, Publisher<Void>> eventMapperIpt) {
        this.client = clientIpt;
        this.gateway = gatewayIpt;
        this.eventMapper = eventMapperIpt;
        this.eventVoid = mapperToEvent(gateway, eventClass, eventMapper);
    }


    /**
     * Maps the event to a Mono<Void> based on the
     * event class and the eventMapper function.
     *
     * @param gatewayIpt the GatewayDiscordClient
     *                for interacting with the Discord gateway
     * @param eventClass the class of the event to handle
     * @param eventMapperIpt the function that maps
     *                    the event to a publisher of void
     * @return a Mono<Void> that represents the event's execution
     */
    public Mono<Void> mapperToEvent(final GatewayDiscordClient gatewayIpt,
                                    final Class<E> eventClass,
                                    final Function<E, Publisher<Void>>
                                            eventMapperIpt) {
        return gatewayIpt.on(eventClass, eventMapperIpt).then();
    }

    /**
     * Retrieves the executable Mono<Void> for the event.
     *
     * @return the Mono<Void> representing the event's execution
     */
    public Mono<Void> getExecutableEvent() {
        return eventVoid;
    }

    /**
     * Returns a default empty Mono<Void> for when no event is processed.
     *
     * @return an empty Mono<Void>
     */
    public static Mono<Void> defaultReturn() {
        return Mono.empty();
    }

    /**
     * Combines the current event handler with another
     * event handler into a parallel event.
     *
     * @param other the other event handler to combine with
     * @return a ParallelEventCombiner that represents
     * the combination of both events
     */
    public ParallelEventCombiner union(
            final EventHandler<? extends Event> other) {
        return new ParallelEventCombiner(this.client,
                this.getExecutableEvent().and(
                        other.getExecutableEvent()));
    }
}
