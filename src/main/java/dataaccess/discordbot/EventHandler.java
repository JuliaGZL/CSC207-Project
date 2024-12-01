package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

/**
 * Handles Discord events of a specified type.
 *
 * @param <E> the type of event to handle
 */
public class EventHandler<E extends Event> {
  DiscordClient client;
  Mono<Void> eventVoid;
  GatewayDiscordClient gateway;
  Function<E, Publisher<Void>> eventMapper;

  /**
   * Constructs an EventHandler with the specified client, gateway, and event void.
   *
   * @param client the Discord client
   * @param gateway the gateway Discord client
   * @param eventVoid the Mono representing the event handling logic
   */
  public EventHandler(DiscordClient client, GatewayDiscordClient gateway, Mono<Void> eventVoid) {
    this.client = client;
    this.eventVoid = eventVoid;
    this.gateway = gateway;
  }

  /**
   * Constructs an EventHandler with the specified client, gateway, event class, and event mapper.
   *
   * @param client the Discord client
   * @param gateway the gateway Discord client
   * @param eventClass the class of the event to handle
   * @param eventMapper the function to map the event to a publisher
   */
  public EventHandler(DiscordClient client, GatewayDiscordClient gateway, Class<E> eventClass,
      Function<E, Publisher<Void>> eventMapper) {
    this.client = client;
    this.gateway = gateway;
    this.eventMapper = eventMapper;
    this.eventVoid = mapperToEvent(gateway, eventClass, eventMapper);
  }

  /**
   * Maps the specified event class to the event mapper 
   * and returns a Mono representing the event handling logic.
   *
   * @param gateway the gateway Discord client
   * @param eventClass the class of the event to handle
   * @param eventMapper the function to map the event to a publisher
   * @return a Mono representing the event handling logic
   */
  public Mono<Void> mapperToEvent(GatewayDiscordClient gateway, Class<E> eventClass,
      Function<E, Publisher<Void>> eventMapper) {
    return gateway.on(eventClass, eventMapper).then();
  }

  /**
   * Returns the Mono representing the event handling logic.
   *
   * @return the Mono representing the event handling logic
   */
  public Mono<Void> getExecutableEvent() {
    return eventVoid;
  }

  /**
   * Returns a Mono that completes immediately.
   *
   * @return a Mono that completes immediately
   */
  public static Mono<Void> defaultReturn() {
    return Mono.empty();
  }

  /**
   * Combines this event handler with another event handler to handle events in parallel.
   *
   * @param other the other event handler
   * @return a ParallelEventCombiner that combines the event handling logic of both handlers
   */
  public ParallelEventCombiner union(EventHandler<? extends Event> other) {
    return new ParallelEventCombiner(
      this.client, this.getExecutableEvent().and(other.getExecutableEvent()));
  }
}
