package dataaccess.discordbot;

/**
 * DefaultBotBuilder is a concrete implementation of BotBuilder that initializes
 * a ChatBot with a set of predefined event handlers.
 */
public class DefaultBotBuilder extends BotBuilder {
  String token;

  /**
   * Constructs a DefaultBotBuilder with a default token.
   */
  public DefaultBotBuilder() {
    String token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";
    this.token = token;
    bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(StatusReportHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
  }

  /**
   * Constructs a DefaultBotBuilder with the specified token.
   *
   * @param token the token to be used by the ChatBot
   */
  public DefaultBotBuilder(String token) {
    this.token = token;
    bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(StatusReportHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
  }

  /**
   * Returns the ChatBot instance.
   *
   * @return the ChatBot instance
   */
  public ChatBot getBot() {
    return bot;
  }

  /**
   * Activates the ChatBot to start running.
   */
  public void activateBot() {
    bot.run();
  }

  /**
   * Retrieves the message from the MessageHolder.
   *
   * @return the message from the MessageHolder
   */
  public String getMessage() {
    return MessageHolder.getMessage();
  }
}
