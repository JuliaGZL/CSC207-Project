package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;

public interface EventHandlerFactory {
    public EventHandler createEventHandler(Class<? extends EventHandler> eventType,
                                           DiscordClient client, GatewayDiscordClient gateway);
}
