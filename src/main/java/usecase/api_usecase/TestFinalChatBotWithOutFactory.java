package usecase.api_usecase;

public class TestFinalChatBotWithOutFactory {
    public static void main(String[] args) {
        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";

        ChatBot bot = new ChatBot(token);
        bot.addEvent(LoginEvent.class);
        bot.addEvent(GreetingEvent.class);
        bot.addEvent(QuitBotEvent.class);
        bot.addEvent(MahjongComboInteractor.class);
        System.out.println(bot.subEventsClasses);
        // [class usecase.api_usecase.LoginEvent, class usecase.api_usecase.GreetingEvent,
        // class usecase.api_usecase.MahjongComboInteractor, class usecase.api_usecase.QuitBotEvent]
        bot.run();
        System.out.println(bot.subEventsClasses);


        System.out.println("First round ended");

        // DefaultBotFactory factory = new DefaultBotFactory();
        // factory.getBot().run();
    }
}
