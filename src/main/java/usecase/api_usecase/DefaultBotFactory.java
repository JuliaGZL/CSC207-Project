package usecase.api_usecase;

import discord4j.core.event.domain.message.MessageEvent;

public class DefaultBotFactory {
    String token;
    ChatBot bot;

    public DefaultBotFactory() {
        String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";
        this.token = token;
        bot = new ChatBot(token);
        bot.addEvent(LoginEvent.class);
        bot.addEvent(GreetingEvent.class);
        bot.addEvent(StatusEvent.class);
        bot.addEvent(MahjongComboInteractor.class);
        bot.addEvent(QuitBotEvent.class);
    }

    public DefaultBotFactory(String token) {
        this.token = token;
        bot = new ChatBot(token);
    }

    public ChatBot getBot() {
        return bot;
    }

    public void run() {
        bot.run();
    }
}
