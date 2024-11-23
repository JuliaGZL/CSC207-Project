package usecase.remove_tile.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;

public class SubEventFactory {
    public SubEventCreator createSubEvent(Class<? extends SubEventCreator> eventType,
                                          DiscordClient client, GatewayDiscordClient gateway) {
        if (eventType.equals(LoginEvent.class)) {
            return new LoginEvent(client, gateway);
        }
        if (eventType.equals(MahjongComboInteractor.class)) {
            return new MahjongComboInteractor(client, gateway);
        }
        if (eventType.equals((GreetingEvent.class))) {
            return new GreetingEvent(client, gateway);
        }
        if (eventType.equals(StatusEvent.class)) {
            return new StatusEvent(client, gateway);
        }
        throw new IllegalArgumentException("Unknown event type: " + eventType);
    }
}
