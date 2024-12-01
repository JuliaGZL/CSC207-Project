package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

/**
 * Abstract class representing an event handler for Discord events.
 *
 * @param <E> the type of event this handler processes
 */
public abstract class EventHandler<E extends Event> {
  DiscordClient client;
  Mono<Void> eventVoid;
  GatewayDiscordClient gateway;
  Function<E, Publisher<Void>> eventMapper;

  /**
   * Constructs an EventHandler with the specified client, gateway, and event void.
   *
   * @param client the Discord client
   * @param gateway the gateway Discord client
   * @param eventVoid the Mono representing the event void
   */
  public EventHandler(DiscordClient client, GatewayDiscordClient gateway, Mono<Void> eventVoid){
    this.client = client;
    this.eventVoid = eventVoid;
    this.gateway = gateway;
  }

  /**
   * Constructs an EventHandler with the specified client, gateway, event class, and event mapper.
   *
   * @param client the Discord client
   * @param gateway the gateway Discord client
   * @param eventClass the class of the event
   * @param eventMapper the function mapping the event to a publisher
   */
  public EventHandler(DiscordClient client, GatewayDiscordClient gateway, Class<E> eventClass,
            Function<E, Publisher<Void>> eventMapper) {
    this.client = client;
    this.gateway = gateway;
    this.eventMapper = eventMapper;
    this.eventVoid = mapperToEvent(gateway, eventClass, eventMapper);
  }

  /**
   * Maps the specified event class to the event mapper and returns a Mono representing the event.
   *
   * @param gateway the gateway Discord client
   * @param eventClass the class of the event
   * @param eventMapper the function mapping the event to a publisher
   * @return a Mono representing the event
   */
  public Mono<Void> mapperToEvent(GatewayDiscordClient gateway, Class<E> eventClass,
                  Function<E, Publisher<Void>> eventMapper){
    return gateway.on(eventClass, eventMapper).then();
  }

  /**
   * Returns the executable event as a Mono.
   *
   * @return the executable event as a Mono
   */
  public Mono<Void> getExecutableEvent() {
    return eventVoid;
  }

  /**
   * Returns a default Mono that completes empty.
   *
   * @return a default Mono that completes empty
   */
  public static Mono<Void> defaultReturn() {
    return Mono.empty();
  }

  /**
   * Combines this event handler with another event handler to run in parallel.
   *
   * @param other the other event handler
   * @return a ParallelEventCombiner combining both event handlers
   */
  public ParallelEventCombiner union(EventHandler<? extends Event> other) {
    return new ParallelEventCombiner(
        this.client, this.getExecutableEvent().and(other.getExecutableEvent()));
  }
}
