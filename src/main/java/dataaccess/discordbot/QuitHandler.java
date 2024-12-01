package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

/**
 * Handles the quit command for the Discord bot.
 */
public class QuitHandler extends MessageHandler {
  /**
   * Constructs a QuitHandler.
   *
   * @param client  the Discord client
   * @param gateway the GatewayDiscordClient
   */
  public QuitHandler(DiscordClient client, GatewayDiscordClient gateway) {
    super(client, gateway, MessageCreateEvent.class, event -> {
      String memberName = getMemberName(event);
      String content = getContent(event);

      if (invokeMessage(content)) {
        System.out.println("Invoke Leaving.");
        return sendMessage(event, newMessage(content, memberName)).and(gateway.logout());
      }

      return EventHandler.defaultReturn();
    });
  }

  /**
   * Creates a new message to be sent when the quit command is invoked.
   *
   * @param content    the content of the message
   * @param memberName the name of the member who sent the message
   * @return the new message
   */
  public static String newMessage(String content, String memberName) {
    return "See ya'll! Especially you,  " + memberName;
  }

  /**
   * Checks if the message is the quit command.
   *
   * @param message the message to check
   * @return true if the message is the quit command, false otherwise
   */
  public static boolean invokeMessage(String message) {
    return message.equals("!vc leave");
  }
}
