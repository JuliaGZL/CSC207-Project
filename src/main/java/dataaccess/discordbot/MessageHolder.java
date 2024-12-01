package dataaccess.discordbot;

/**
 * A holder for a message string.
 */
public class MessageHolder {
  static String msg = "";

  /**
   * Sets the message.
   *
   * @param msg the message to set
   */
  static void setMessage(String msg) {
    MessageHolder.msg = msg;
  }

  /**
   * Gets the message without the first word.
   *
   * @return the message without the first word
   */
  static String getMessage() {
    return msg.substring(msg.indexOf(' ') + 1);
  }
}
