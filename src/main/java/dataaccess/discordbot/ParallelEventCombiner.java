package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import reactor.core.publisher.Mono;

/**
 * Combines multiple Discord events to be executed in parallel.
 */
public class ParallelEventCombiner {
  DiscordClient client;
  Mono<Void> event;

  /**
   * Constructs a ParallelEventCombiner with the specified client and event.
   *
   * @param client the Discord client
   * @param event the event to be executed
   */
  public ParallelEventCombiner(DiscordClient client, Mono<Void> event) {
    this.client = client;
    this.event = event;
  }

  /**
   * Combines this event with another ParallelEventCombiner's event.
   *
   * @param other the other ParallelEventCombiner
   * @return a new ParallelEventCombiner with combined events
   */
  public ParallelEventCombiner union(ParallelEventCombiner other) {
    return new ParallelEventCombiner(client, this.event.and(other.event));
  }

  /**
   * Combines this event with an EventHandler's event.
   *
   * @param other the EventHandler
   * @return a new ParallelEventCombiner with combined events
   */
  public ParallelEventCombiner union(EventHandler other) {
    return new ParallelEventCombiner(client, this.event.and(other.getExecutableEvent()));
  }

  /**
   * Returns the combined event.
   *
   * @return the combined event
   */
  public Mono<Void> getEvent() {
    return event;
  }
}
