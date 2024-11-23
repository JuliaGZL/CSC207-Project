package usecase.clear_tiles.api_usecase;

public class TestFinalChatBot {
    public static void main(String[] args) {
        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        ChatBot bot = new ChatBot(token);
        bot.addEvent(LoginEvent.class);
        bot.addEvent(GreetingEvent.class);
        bot.run();
    }
}
