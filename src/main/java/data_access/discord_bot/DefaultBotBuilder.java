package data_access.discord_bot;


/**
 * Default Botbuilder which implements BotBuilder interface.
 */
public class DefaultBotBuilder implements BotBuilder {

    /**
     * Token used for bot authentication.
     */
    private final String token;

    /**
     * The ChatBot instance.
     */
    private final ChatBot bot;

    /**
     * Default token used for authentication.
     */
    private static final String DEFAULT_TOKEN =
            "MTEzODg1MzczMzQ2MDEwMzMzOQ."
                    + "G9wYsT."
                    + "OpiQGsUnUJ4KoIKmSD0L9CDCETNzQhZP"
                    + "BCDFto";


    /**
     * Constructor with default token.
     * Initializes the bot and adds event handlers.
     */
    public DefaultBotBuilder() {
        final String defaultToken = DEFAULT_TOKEN;
        this.token = defaultToken;
        bot = new ChatBot(token);
        initializeBotEvent();
    }

    /**
     * Initializes the bot with event handlers.
     */
    private void initializeBotEvent() {
        bot.addEvent(LoginHandler.class);
        bot.addEvent(GreetingHandler.class);
        bot.addEvent(StatusReportHandler.class);
        bot.addEvent(MahjongComboHandler.class);
        bot.addEvent(QuitHandler.class);
    }

    /**
     * Constructor with custom token.
     * Initializes the bot and adds event handlers.
     *
     * @param tokenIpt the custom token to use for authentication
     */
    public DefaultBotBuilder(final String tokenIpt) {
        this.token = tokenIpt;
        bot = new ChatBot(token);
        initializeBotEvent();
    }

    /**
     * Gets the bot instance.
     *
     * @return the bot instance
     */
    public ChatBot getBot() {
        return bot;
    }

    /**
     * Activates the bot by running it.
     */
    public void activateBot() {
        bot.run();
    }

    /**
     * Retrieves the current message.
     *
     * @return the current message
     */
    public String getMessage() {
        return MessageHolder.getMessage();
    }
}
