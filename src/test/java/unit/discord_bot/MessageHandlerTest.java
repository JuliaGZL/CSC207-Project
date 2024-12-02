package unit.discord_bot;

import dataaccess.discordbot.MessageHandler;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


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
