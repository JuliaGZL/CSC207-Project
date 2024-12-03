package data_access.discord_bot.example;

import data_access.discord_bot.*;

public class TestChatBotWithPartialFunctions {
    public static void main(String[] args) {
        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        ChatBot bot = new ChatBot(token);
        bot.addEvent(LoginHandler.class);
        bot.addEvent(GreetingHandler.class);
        bot.addEvent(MahjongComboHandler.class);
        bot.addEvent(QuitHandler.class);
        bot.run();

        System.out.println("End Testing");
    }
}
