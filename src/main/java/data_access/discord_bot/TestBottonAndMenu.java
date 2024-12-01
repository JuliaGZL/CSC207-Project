package data_access.discord_bot;

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
                // final String guildId = "1212985746688974848";

                String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

                DiscordClient.create(token)
                                .withGateway(gw -> {
                                        Mono<Message> sendMessage = gw.on(GuildCreateEvent.class)
                                                        .filter(e -> e.getGuild().getId().asString().equals(guildId))
                                                        .next()
                                                        .flatMap(e -> e.getGuild()
                                                                        .getChannelById(Snowflake.of(channelId)))
                                                        .ofType(TextChannel.class)
                                                        .flatMap(channel -> channel.createMessage("Click some buttons!")
                                                                        .withComponents(
                                                                                        ActionRow.of(
                                                                                                        // ID, label
                                                                                                        Button.primary("1",
                                                                                                                        "1"),
                                                                                                        Button.primary("2",
                                                                                                                        "2"),
                                                                                                        Button.primary("3",
                                                                                                                        "3"),
                                                                                                        Button.primary("4",
                                                                                                                        "4"),
                                                                                                        Button.primary("5",
                                                                                                                        "5")),
                                                                                        ActionRow.of(
                                                                                                        Button.primary("6",
                                                                                                                        "6"),
                                                                                                        Button.primary("7",
                                                                                                                        "7"),
                                                                                                        Button.primary("8",
                                                                                                                        "8"),
                                                                                                        Button.primary("9",
                                                                                                                        "9"),
                                                                                                        Button.primary("10",
                                                                                                                        "10")),
                                                                                        ActionRow.of(
                                                                                                        Button.primary("11",
                                                                                                                        "11"),
                                                                                                        Button.primary("12",
                                                                                                                        "12"),
                                                                                                        Button.primary("13",
                                                                                                                        "13"),
                                                                                                        Button.primary("14",
                                                                                                                        "14"),
                                                                                                        Button.primary("15",
                                                                                                                        "15")),
                                                                                        ActionRow.of(
                                                                                                        Button.primary("16",
                                                                                                                        "16"),
                                                                                                        Button.primary("17",
                                                                                                                        "17"),
                                                                                                        Button.primary("18",
                                                                                                                        "18"),
                                                                                                        Button.primary("19",
                                                                                                                        "19"),
                                                                                                        Button.primary("20",
                                                                                                                        "20")),
                                                                                        ActionRow.of(
                                                                                                        Button.primary("21",
                                                                                                                        "21"),
                                                                                                        Button.primary("22",
                                                                                                                        "22"),
                                                                                                        Button.primary("23",
                                                                                                                        "23"),
                                                                                                        Button.primary("24",
                                                                                                                        "24"),
                                                                                                        Button.primary("25",
                                                                                                                        "255555"))));

                                        return sendMessage
                                                        .map(Message::getId)
                                                        .flatMapMany(buttonMessageId -> gw.on(
                                                                        ButtonInteractionEvent.class,
                                                                        event -> Mono.justOrEmpty(event.getInteraction()
                                                                                        .getMessage())
                                                                                        .map(Message::getId)
                                                                                        .filter(buttonMessageId::equals)
                                                                                        .then(event.edit(spec -> spec
                                                                                                        .setContent(event
                                                                                                                        .getCustomId())))))
                                                        .then();
                                })
                                .block();
        }
}
