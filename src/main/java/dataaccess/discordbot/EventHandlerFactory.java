package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;

/**
 * Interface for creating event handlers for Discord bot events.
 */
public interface EventHandlerFactory {

    /**
     * Creates an event handler for the specified event type.
     *
     * @param eventType the class type of the
     *                  event handler to create
     * @param client the Discord client used
     *               to connect to Discord
     * @param gateway the GatewayDiscordClient
     *                used to interact with the Discord gateway
     * @return the created event handler
     */
    EventHandler createEventHandler(
            Class<? extends EventHandler> eventType,
            DiscordClient client,
            GatewayDiscordClient gateway);
}
