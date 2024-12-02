/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
   * @param event  the event to be executed
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
