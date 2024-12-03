package dataaccess.discordbot.example;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;
import discord4j.core.event.domain.guild.GuildCreateEvent;
import discord4j.core.event.domain.interaction.ButtonInteractionEvent;
import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.TextChannel;
import reactor.core.publisher.Mono;

public class TestBottonAndMenu {


    public static void main(String[] args) {

        final String guildId = "1138854818874667118";
        final String channelId = "1138854819575103490";
        // final String channelId = "1212985746688974851";
        //final String guildId = "1212985746688974848";


        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT." +
                "OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";


        DiscordClient.create(token)
                .withGateway(gw -> {
                    Mono<Message> sendMessage = gw.on(
                            GuildCreateEvent.class)
                            .filter(e -> e.getGuild().getId().asString().equals(
                                    guildId))
                            .next()
                            .flatMap(e -> e.getGuild().getChannelById(Snowflake.of(channelId)))
                            .ofType(TextChannel.class)
                            .flatMap(channel ->
                                    channel.createMessage("Choose your tiles!")
                                    .withComponents(
                                            ActionRow.of(
                                                    //              ID,  label
                                                    Button.primary("1", "1m"),
                                                    Button.primary("2", "2m"),
                                                    Button.primary("3", "3m"),
                                                    Button.primary("4", "4m"),
                                                    Button.primary("5", "5m")

                                            ),
                                            ActionRow.of(
                                                    Button.primary("6", "6m"),
                                                    Button.primary("7", "7m"),
                                                    Button.primary("8", "8m"),
                                                    Button.primary("9", "9m")
                                                    // Button.primary("10", "10")
                                            ),
                                            ActionRow.of(
                                                    Button.primary("11", "1s"),
                                                    Button.primary("12", "2s"),
                                                    Button.primary("13", "3s"),
                                                    Button.primary("14", "4s"),
                                                    Button.primary("15", "5s")
                                            ),
                                            ActionRow.of(
                                                    Button.primary("16", "6s"),
                                                    Button.primary("17", "7s"),
                                                    Button.primary("18", "8s"),
                                                    Button.primary("19", "9s")
                                                    // Button.primary("20", "20")
                                            ),
                                            ActionRow.of(
                                                    Button.primary("21", "1p"),
                                                    Button.primary("22", "2p"),
                                                    Button.primary("23", "3p"),
                                                    Button.primary("24", "4p"),
                                                    Button.primary("25", "5p")
                                            )


                                    )
                            );

                    return sendMessage
                            .map(Message::getId)
                            .flatMapMany(buttonMessageId ->
                                    gw.on(ButtonInteractionEvent.class, event ->
                                            Mono.justOrEmpty(event.getInteraction().getMessage())
                                                    .map(Message::getId)
                                                    .filter(buttonMessageId::equals)
                                                    .then(event.edit(spec -> spec.setContent(event.getCustomId())))
                                    )
                            )
                            .then();
                })
                .block();
    }
}

