package dataaccess.discordbot;

/**
 * Interface for building and activating a ChatBot.
 */
public interface BotBuilder {
  /**
   * Retrieves the built ChatBot instance.
   *
   * @return the ChatBot instance
   */
  public ChatBot getBot();

  /**
   * Activates the ChatBot.
   */
  public void activateBot();
}
