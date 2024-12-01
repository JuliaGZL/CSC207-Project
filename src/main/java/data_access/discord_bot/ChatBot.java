package data_access.discord_bot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ChatBot {
    String token;
    DiscordClient client;
    EventHandlerFactory handlerFactory;
    List<Class<? extends EventHandler>> subEventsClasses = new ArrayList<>();
    Mono<Void> onConnect;

    public ChatBot(String token) {
        this.token = token;
        this.client = DiscordClient.create(token);
        this.handlerFactory = new HandlerFactoryByClass();
    }

    public void addEvent(Class<? extends EventHandler> eventType) {
        subEventsClasses.add(eventType);
    }

    public void setMainService() {
        this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
            // Collect all events as Mono<Void> and combine them
            Class<? extends EventHandler> subEventType1 = subEventsClasses.remove(0);
            EventHandler subEvent1 = handlerFactory.createEventHandler(subEventType1, client, gateway);
            if (subEventsClasses.size() == 0) {
                return subEvent1.getExecutableEvent();
            }
            ParallelEventCombiner parellel;

            EventHandler subEvent2 = handlerFactory.createEventHandler(subEventsClasses.remove(0), client, gateway);
            parellel = subEvent1.union(subEvent2);

            for (int i = 0; i < subEventsClasses.size(); i++) {
                parellel = parellel.union(handlerFactory.createEventHandler(subEventsClasses.get(i), client, gateway));
            }
            return parellel.getEvent();
        });
    }

    public void run() {
        setMainService();
        this.onConnect.block();
    }
}
