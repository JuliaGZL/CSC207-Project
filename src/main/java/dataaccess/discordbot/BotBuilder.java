package dataaccess.discordbot;

/**
 * Abstract class for building a ChatBot.
 */
public abstract class BotBuilder {
  ChatBot bot;

  /**
   * Returns the built ChatBot instance.
   *
   * @return the ChatBot instance
   */
  public abstract ChatBot getBot();

  /**
   * Activates the built ChatBot.
   */
  public abstract void activateBot();
}
