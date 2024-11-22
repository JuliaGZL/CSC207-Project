package usecase.clear_tiles.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

public class LoginEvent extends SubEventCreator<ReadyEvent> {

    public LoginEvent(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, ReadyEvent.class, event ->
                Mono.fromRunnable(() -> {
                    final User self = event.getSelf();
                    System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                }));
    }
}
