package unit.discord_bot;

import data_access.discord_bot.FeedbackGenerator;
import data_access.discord_bot.MessageHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.*;

public class MessageHandlerTest {

    @Test
    public void testNewMessageError() {
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    MessageHandler.newMessage("");
                }
        );
    }

    @Test
    public void testInvokeMessageError() {
        Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    MessageHandler.invokeMessage("");
                }
        );
    }
}
