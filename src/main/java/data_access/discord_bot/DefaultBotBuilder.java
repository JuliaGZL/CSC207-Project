package data_access.discord_bot;

public class DefaultBotBuilder implements BotBuilder {
    final String token;
    private final ChatBot bot;

    public DefaultBotBuilder() {
        final String defaultToken = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";
        this.token = defaultToken;
        bot = new ChatBot(token);
        bot.addEvent(LoginHandler.class);
        bot.addEvent(GreetingHandler.class);
        bot.addEvent(StatusReportHandler.class);
        bot.addEvent(MahjongComboHandler.class);
        bot.addEvent(QuitHandler.class);
    }

    public DefaultBotBuilder(String token) {
        this.token = token;
        bot = new ChatBot(token);
        bot.addEvent(LoginHandler.class);
        bot.addEvent(GreetingHandler.class);
        bot.addEvent(StatusReportHandler.class);
        bot.addEvent(MahjongComboHandler.class);
        bot.addEvent(QuitHandler.class);
    }

    public ChatBot getBot() {
        return bot;
    }

    public void activateBot() {
        bot.run();
    }

    public String getMessage(){
        return MessageHolder.getMessage();
    }
}
