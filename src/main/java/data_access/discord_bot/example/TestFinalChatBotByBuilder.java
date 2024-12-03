package data_access.discord_bot.example;

import data_access.discord_bot.DefaultBotBuilder;

public class TestFinalChatBotByBuilder {
    public static void main(String[] args) {

        DefaultBotBuilder factory = new DefaultBotBuilder();
        factory.activateBot();
        System.out.println(factory.getMessage());
    }
}
