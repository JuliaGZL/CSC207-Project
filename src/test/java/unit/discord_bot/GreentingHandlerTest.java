package unit.discord_bot;

import data_access.discord_bot.GreetingHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreentingHandlerTest {
    @Test
    public void testGreetingMessage() {
        String greet = GreetingHandler.newMessage("HeLLO", "bill");
        assertEquals("HeLLO bill", greet);
    }

    @Test
    public void testInvokeGreetingFalse() {
        String msg = "Hello World!";
        assertFalse(GreetingHandler.invokeMessage(msg));
    }

    @Test
    public void testInvokeGreetingTrue() {
        String msg = "HelLo";
        assertTrue(GreetingHandler.invokeMessage(msg));
    }
}
