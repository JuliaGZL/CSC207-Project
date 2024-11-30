package data_access.discord_bot;

public abstract class BotBuilder {
    ChatBot bot;

    public abstract ChatBot getBot();
    public abstract void activateBot();
}
