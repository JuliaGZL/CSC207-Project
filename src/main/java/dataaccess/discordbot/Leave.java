package dataaccess.discordbot;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Member;
import discord4j.voice.VoiceConnection;
import reactor.core.publisher.Mono;

/**
 * Handles the command to leave a voice channel in Discord.
 */
public class Leave {

  private final GatewayDiscordClient client;

  /**
   * Constructs a Leave command handler.
   *
   * @param client the Discord client
   */
  public Leave(GatewayDiscordClient client) {
    this.client = client;
  }

  /**
   * Handles the message creation event to leave a voice channel.
   *
   * @param event the message creation event
   * @return a Mono that completes when the bot has left the voice channel
   */
  public Mono<Void> onMessageCreate(MessageCreateEvent event) {
    if (event.getMessage().getContent().equals("!vc leave")) {
      return Mono.justOrEmpty(event.getMember())
          .flatMap(Member::getVoiceState)
          .flatMap(vs -> client.getVoiceConnectionRegistry()
              .getVoiceConnection(vs.getGuildId())
              .doOnSuccess(vc -> {
                if (vc == null) {
                  System.out.println("No voice connection to leave!");
                }
              }))
          .flatMap(VoiceConnection::disconnect);
    }
    return Mono.empty();
  }
}