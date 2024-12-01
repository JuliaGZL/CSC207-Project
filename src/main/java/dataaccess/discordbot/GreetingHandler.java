package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.Arrays;
import java.util.List;

/**
 * Handles greeting messages in a Discord bot.
 */
public class GreetingHandler extends MessageHandler {
  static final List<String> GREETING_LIST = Arrays.asList("hello", "hi", "hey", "你好");

  /**
   * Constructs a GreetingHandler.
   *
   * @param client the Discord client
   * @param gateway the GatewayDiscordClient
   */
  public GreetingHandler(DiscordClient client, GatewayDiscordClient gateway) {
    super(client, gateway, MessageCreateEvent.class, event -> {
      String memberName = getMemberName(event);
      String content = getContent(event);

      if (invokeMessage(content)) {
        return sendMessage(event, newMessage(content, memberName));
      }

      return EventHandler.defaultReturn();
    });
  }

  /**
   * Creates a new greeting message.
   *
   * @param content the content of the message
   * @param memberName the name of the member
   * @return the new greeting message
   */
  public static String newMessage(String content, String memberName) {
    return content + " " + memberName;
  }

  /**
   * Checks if the message is a greeting.
   *
   * @param message the message to check
   * @return true if the message is a greeting, false otherwise
   */
  public static boolean invokeMessage(String message) {
    return GREETING_LIST.stream().anyMatch(message::equalsIgnoreCase);
  }
}
