package data_access.discord_bot;

/**
 * A utility class for holding and retrieving a message.
 *
 * This class provides methods to set and get a message in a
 * static variable. The message is stored
 * as a {@link String}, and the getMessage() method processes the
 * message to exclude the first word.
 */
public class MessageHolder {

    /**
     * The static message variable that holds the current message.
     * It is initialized as an empty string.
     */
    private static String msg =  "";

    /**
     * Sets the message to the provided string.
     *
     * This method updates the static msg variable to store the given message.
     *
     * @param msg the message to be stored
     */
    static void setMessage(final String msg) {
        MessageHolder.msg = msg;
    }

    /**
     * Retrieves the message excluding the first word.
     *
     * This method processes the stored message by extracting
     * the substring starting from
     * the second word onwards (after the first space).
     *
     * @return the message without the first word
     * @throws StringIndexOutOfBoundsException if
     * there is no space in the message
     */
    static String getMessage() {
        return msg.substring(msg.indexOf(' ') + 1);
    }
}
