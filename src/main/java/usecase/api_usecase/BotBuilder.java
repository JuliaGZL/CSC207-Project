package usecase.api_usecase;

public abstract class BotBuilder {
    ChatBot bot;

    public abstract ChatBot getBot();
    public abstract void activateBot();
}
