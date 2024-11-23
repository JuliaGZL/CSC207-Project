package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;


public class SubEventFactory {
    public SubEventCreator createSubEvent(Class<? extends SubEventCreator> eventType,
                                          DiscordClient client, GatewayDiscordClient gateway) {
        if (eventType.equals(LoginEvent.class)) {
            System.out.println("Login Event Created");
            return new LoginEvent(client, gateway);
        }
        if (eventType.equals(MahjongComboInteractor.class)) {
            System.out.println("Mahjong Event Created");
            return new MahjongComboInteractor(client, gateway);
        }
        if (eventType.equals(GreetingEvent.class)) {
            System.out.println("Greating Event Created");
            return new GreetingEvent(client, gateway);
        }
        if (eventType.equals(StatusEvent.class)) {
            System.out.println("Status Event Created");
            return new StatusEvent(client, gateway);
        }
        if (eventType.equals(QuitBotEvent.class)) {
            System.out.println("Quit Bot Event Created");
            return new QuitBotEvent(client, gateway);
        }
        throw new IllegalArgumentException("Unknown event type: " + eventType);
    }
}
