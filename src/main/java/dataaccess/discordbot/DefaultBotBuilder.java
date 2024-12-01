package dataaccess.discordbot;

/**
 * DefaultBotBuilder is responsible for building and configuring a ChatBot instance.
 */
public class DefaultBotBuilder implements BotBuilder {
  final String token;
  private final ChatBot bot;

  /**
   * Constructs a DefaultBotBuilder with a default token.
   */
  public DefaultBotBuilder() {
    this.token = "MTEzODg1MzczMzQ2MDEwMzMzOQ.G9wYsT.OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZPBCDFto";
    bot = new ChatBot(token);
    bot.addEvent(LoginHandler.class);
    bot.addEvent(GreetingHandler.class);
    bot.addEvent(StatusReportHandler.class);
    bot.addEvent(MahjongComboHandler.class);
    bot.addEvent(QuitHandler.class);
  }

  /**
   * Constructs a DefaultBotBuilder with a specified token.
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
   * Returns the configured ChatBot instance.
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
   * Retrieves a message from the MessageHolder.
   *
   * @return the message from the MessageHolder
   */
  public String getMessage(){
    return MessageHolder.getMessage();
  }
}
