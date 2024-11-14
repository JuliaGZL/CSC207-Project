package app;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class LoginEventCreator extends SubEventCreator<ReadyEvent> {
    public LoginEventCreator(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, ReadyEvent.class, event ->
                Mono.fromRunnable(() -> {
                    final User self = event.getSelf();
                    System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                }));
    }
}
