package dataaccess.discordbot;

/**
 * Interface for building and managing a bot.
 */
public interface BotBuilder {

    /**
     * Retrieves the bot instance.
     *
     * @return the bot instance
     */
    ChatBot getBot();

    /**
     * Activates the bot to start running.
     */
    void activateBot();
}
