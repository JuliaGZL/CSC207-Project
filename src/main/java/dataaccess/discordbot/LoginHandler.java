package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import reactor.core.publisher.Mono;

/**
 * Event handler for when the bot successfully logs in to Discord.
 *
 * This handler listens for the {@link ReadyEvent},
 * which is triggered when the bot has
 * logged in and is ready to interact with the Discord gateway.
 * It prints the bot's username and discriminator when it logs in.
 */
public class LoginHandler extends EventHandler<ReadyEvent> {

    /**
     * Constructs a LoginHandler to handle the bot's login event.
     *
     * When the bot logs in, this handler will print
     * the bot's username and discriminator.
     *
     * @param client the Discord client used to interact with Discord
     * @param gateway the GatewayDiscordClient used to
     *                interact with the Discord gateway
     */
    public LoginHandler(final DiscordClient client,
                        final GatewayDiscordClient gateway) {
        super(client, gateway, ReadyEvent.class, event ->
                Mono.fromRunnable(() -> {
                    final User self = event.getSelf();
                    System.out.printf("Logged in as %s#%s%n",
                            self.getUsername(), self.getDiscriminator());
                }));
    }
}
