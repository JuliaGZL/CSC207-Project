package data_access.discord_bot;

public class MessageHolder {
    static String msg = "";

    static void setMessage(String msg) {
        MessageHolder.msg = msg;
    }

    static String getMessage() {
        return msg.substring(msg.indexOf(' ') + 1);
    }
}
