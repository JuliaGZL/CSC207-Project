package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;

/**
 * Factory interface for creating event handlers.
 */
public interface EventHandlerFactory {

  /**
   * Creates an event handler for the specified event type.
   *
   * @param eventType the class of the event handler to create
   * @param client the Discord client
   * @param gateway the gateway Discord client
   * @return the created event handler
   */
  public EventHandler createEventHandler(Class<? extends EventHandler> eventType,
      DiscordClient client, GatewayDiscordClient gateway);
}
