package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ChatBot {
    String token;
    DiscordClient client;
    SubEventFactory subEventFactory;
    List<Class<? extends SubEventCreator>> subEventsClasses = new ArrayList<>();
    Mono<Void> onConnect;

    public ChatBot(String token) {
        this.token = token;
        this.client = DiscordClient.create(token);
        this.subEventFactory = new SubEventFactory();
    }

    public void addEvent(Class<? extends SubEventCreator> eventType) {
        subEvents.add(eventType);
    }

    public void setMainService(){
        this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
            // Collect all events as Mono<Void> and combine them

        });
    }

    public void run(){
        setMainService();
        this.onConnect.block();
    }
}
