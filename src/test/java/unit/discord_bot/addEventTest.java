package unit.discord_bot;

import data_access.discord_bot.LoginHandler;
import data_access.discord_bot.MahjongComboHandler;
import dataaccess.discordbot.ChatBot;
import dataaccess.discordbot.GreetingHandler;
import dataaccess.discordbot.QuitHandler;

import org.junit.Test;
import static org.junit.Assert.*;

public class addEventTest {
    @Test
    public void addEventListMatch() {
        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        ChatBot bot = new ChatBot(token);
        bot.addEvent(LoginHandler.class);
        bot.addEvent(GreetingHandler.class);
        bot.addEvent(MahjongComboHandler.class);
        bot.addEvent(QuitHandler.class);
        System.out.println(bot.getSubEventsClasses());
        String expected = "[class data_access.discord_bot.LoginHandler, "+
                "class data_access.discord_bot.GreetingHandler, "+
                "class data_access.discord_bot.MahjongComboHandler, "+
                "class data_access.discord_bot.QuitHandler]";
        assertEquals(expected, bot.getSubEventsClasses().toString());
    }
}
