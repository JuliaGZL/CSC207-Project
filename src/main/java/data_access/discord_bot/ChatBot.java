package data_access.discord_bot;

import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Mono;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.DiscordClient;

/**
 * Represents a chatbot that interacts with Discord
 * through various event handlers.
 */
public class ChatBot {

    /**
     * The authentication token for the bot.
     */
    private String token;

    /**
     * The Discord client instance used to connect to Discord.
     */
    private DiscordClient client;

    /**
     * The factory responsible for creating event handlers.
     */
    private EventHandlerFactory handlerFactory;

    /**
     * List of event handler classes to process different events.
     */
    private List<Class<? extends EventHandler>>
            subEventsClasses = new ArrayList<>();

    /**
     * The main service for the bot to connect to Discord.
     */
    private Mono<Void> onConnect;

    /**
     * Constructs a ChatBot instance with the given authentication token.
     *
     * @param token the authentication token for the bot
     */
    public ChatBot(final String token) {
        this.token = token;
        this.client = DiscordClient.create(token);
        this.handlerFactory = new HandlerFactoryByClass();
    }

    /**
     * Adds an event type to the list of event handlers for the bot.
     *
     * @param eventType the event type class to be handled by the bot
     */
    public void addEvent(final Class<? extends EventHandler> eventType) {
        subEventsClasses.add(eventType);
    }

    /**
     * Sets the main service for the bot to connect
     * to Discord and handle events.
     */
    public void setMainService() {
        this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
            // Collect all events as
            // Mono<Void> and combine them
            Class<? extends EventHandler>
                    subEventType1 = subEventsClasses.remove(0);
            EventHandler subEvent1 =
                    handlerFactory.createEventHandler(
                            subEventType1, client, gateway);
            if (subEventsClasses.size() == 0) {
                return subEvent1.getExecutableEvent();
            }
            ParallelEventCombiner parellel;

            EventHandler subEvent2 = handlerFactory.createEventHandler(
                    subEventsClasses.remove(0), client, gateway);
            parellel = subEvent1.union(subEvent2);

            for (int i = 0; i < subEventsClasses.size(); i++) {
                parellel = parellel.union(
                        handlerFactory.createEventHandler(
                                subEventsClasses.get(i), client, gateway));
            }
            return parellel.getEvent();
        });
    }

    /**
     * Runs the bot by initializing the main service
     * and blocking until the bot is connected.
     */
    public void run() {
        setMainService();
        this.onConnect.block();
    }

    /**
     * Gets the list of event handler classes
     * that are registered for the bot.
     *
     * @return the list of event handler classes
     */
    public List<Class<? extends EventHandler>> getSubEventsClasses() {
        return subEventsClasses;
    }
}
