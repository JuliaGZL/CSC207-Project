package data_access.discord_bot;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;
import discord4j.core.object.entity.Member;
import discord4j.voice.VoiceConnection;

/**
 * Handles the "leave" command for voice connections in a Discord bot.
 *
 * This class listens for the "!vc leave" command in a message and disconnects
 * the bot from the voice channel if the bot is connected.
 */
public class Leave  {

    /**
     * The Discord client used to interact with the Discord gateway.
     */
    private final GatewayDiscordClient client;

    /**
     * Constructs a Leave handler that will disconnect the
     * bot from voice channels.
     *
     * @param client the GatewayDiscordClient used to interact with Discord
     */
    public Leave(final GatewayDiscordClient client) {
        this.client = client;
    }

    /**
     * Handles the "leave" command for disconnecting from a voice channel.
     *
     * When the message "!vc leave" is received, the bot will
     * attempt to disconnect
     * from the voice channel it is currently connected to, if any.
     *
     * @param event the MessageCreateEvent representing the
     *              incoming message
     * @return a Mono indicating the success or failure
     * of the disconnection process
     */
    public Mono<Void> onMessageCreate(final MessageCreateEvent event) {
        if (event.getMessage().getContent().equals("!vc leave")) {
            return Mono.justOrEmpty(event.getMember())
                    .flatMap(Member::getVoiceState)
                    .flatMap(vs -> client.getVoiceConnectionRegistry()
                            .getVoiceConnection(vs.getGuildId())
                            .doOnSuccess(vc -> {
                                if (vc == null) {
                                    System.out.println(
                                            "No voice connection to leave!");
                                }
                            }))
                    .flatMap(VoiceConnection::disconnect);
        }
        return Mono.empty();
    }
}