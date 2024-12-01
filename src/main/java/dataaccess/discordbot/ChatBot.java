package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Mono;

/**
 * ChatBot is responsible for managing the connection to Discord and handling
 * events.
 */
public class ChatBot {
  String token;
  DiscordClient client;
  EventHandlerFactory handlerFactory;
  List<Class<? extends EventHandler>> subEventsClasses = new ArrayList<>();
  Mono<Void> onConnect;

  /**
   * Constructs a ChatBot with the specified token.
   *
   * @param token the token used to authenticate with Discord
   */
  public ChatBot(String token) {
    this.token = token;
    this.client = DiscordClient.create(token);
    this.handlerFactory = new HandlerFactoryByClass();
  }

  /**
   * Adds an event handler class to the list of events to be handled.
   *
   * @param eventType the class of the event handler to be added
   */
  public void addEvent(Class<? extends EventHandler> eventType) {
    subEventsClasses.add(eventType);
  }

  /**
   * Sets up the main service to handle events by combining all event handlers.
   */
  public void setMainService() {
    this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
      // Collect all events as Mono<Void> and combine them
      Class<? extends EventHandler> subEventType1 = subEventsClasses.remove(0);
      EventHandler subEvent1 = handlerFactory.createEventHandler(subEventType1, client, gateway);
      if (subEventsClasses.size() == 0) {
        return subEvent1.getExecutableEvent();
      }
      ParallelEventCombiner parellel;

      EventHandler subEvent2 = handlerFactory.createEventHandler(
          subEventsClasses.remove(0), client, gateway);
      parellel = subEvent1.union(subEvent2);

      for (int i = 0; i < subEventsClasses.size(); i++) {
        parellel = parellel.union(
            handlerFactory.createEventHandler(subEventsClasses.get(i), client, gateway));
      }
      return parellel.getEvent();
    });
  }

  /**
   * Runs the ChatBot by setting up the main service and blocking until the
   * connection is closed.
   */
  public void run() {
    setMainService();
    this.onConnect.block();
  }
}
