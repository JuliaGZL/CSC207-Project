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
        subEventsClasses.add(eventType);
    }

    public void setMainService(){
        this.onConnect = client.withGateway((GatewayDiscordClient gateway) -> {
            // Collect all events as Mono<Void> and combine them
            Class<? extends SubEventCreator> subEventType1 = subEventsClasses.remove(0);
            SubEventCreator subEvent1 = subEventFactory.createSubEvent(subEventType1, client, gateway);
            if (subEventsClasses.size() == 0){
                return subEvent1.getExecutableEvent();
            }
            ParallelEvent parellel;

            SubEventCreator subEvent2 = subEventFactory.createSubEvent(subEventsClasses.remove(0), client, gateway);
            parellel = subEvent1.union(subEvent2);

            for (int i = 0; i < subEventsClasses.size(); i++){
                parellel = parellel.union(subEventFactory.createSubEvent(subEventsClasses.get(0), client, gateway));
            }
            return parellel.getEvent();
        });
    }

    public void run(){
        setMainService();
        this.onConnect.block();
    }
}
