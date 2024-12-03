package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;

/**
 * Factory class that creates event handlers for various event types.
 *
 * This class implements the {@link EventHandlerFactory}
 * interface and provides the functionality to create
 * specific event handlers based on the class type of the event.
 */
public class HandlerFactoryByClass implements EventHandlerFactory {

    /**
     * Creates an event handler based on the specified event type.
     *
     * This method creates the appropriate event handler for
     * the given event class type
     * and initializes it with the provided Discord client and gateway.
     *
     * @param eventType the class type of the event handler to create
     * @param client the Discord client used to connect to Discord
     * @param gateway the GatewayDiscordClient used to interact
     *                with the Discord gateway
     * @return the created event handler
     * @throws IllegalArgumentException if the provided
     * event type is not recognized
     */
    @Override
    public EventHandler createEventHandler(final Class<? extends EventHandler>
                                                       eventType,
                                           final DiscordClient client,
                                           final GatewayDiscordClient gateway) {
        if (eventType.equals(LoginHandler.class)) {
            System.out.println("Login Event Created");
            return new LoginHandler(client, gateway);
        }
        if (eventType.equals(MahjongComboHandler.class)) {
            System.out.println("Mahjong Event Created");
            return new MahjongComboHandler(client, gateway);
        }
        if (eventType.equals(GreetingHandler.class)) {
            System.out.println("Greating Event Created");
            return new GreetingHandler(client, gateway);
        }
        if (eventType.equals(StatusReportHandler.class)) {
            System.out.println("Status Event Created");
            return new StatusReportHandler(client, gateway);
        }
        if (eventType.equals(QuitHandler.class)) {
            System.out.println("Quit Bot Event Created");
            return new QuitHandler(client, gateway);
        }
        throw new IllegalArgumentException("Unknown event type: " + eventType);
    }

}
