package usecase.api_usecase;

import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;
import discord4j.core.object.entity.Member;
import discord4j.voice.VoiceConnection;

import java.beans.EventHandler;

public class Leave  {

    private final GatewayDiscordClient client;

    public Leave(GatewayDiscordClient client) {
        this.client = client;
    }


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