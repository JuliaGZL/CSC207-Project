package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;


public class SubEventFactory {
    public EventHandler createSubEvent(Class<? extends EventHandler> eventType,
                                       DiscordClient client, GatewayDiscordClient gateway) {
        if (eventType.equals(LoginHandler.class)) {
            System.out.println("Login Event Created");
            return new LoginHandler(client, gateway);
        }
        if (eventType.equals(MahjongComboInteractor.class)) {
            System.out.println("Mahjong Event Created");
            return new MahjongComboInteractor(client, gateway);
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
