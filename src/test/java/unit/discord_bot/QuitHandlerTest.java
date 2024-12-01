package unit.discord_bot;

import data_access.discord_bot.QuitHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuitHandlerTest {
    @Test
    public void testQuitMessage() {
        String exampleMessage = QuitHandler.newMessage("the content", "paul");
        assertEquals("See ya'll! Especially you, paul", exampleMessage);
    }

    @Test
    public void testQuitInvokeConditionTrue() {
        boolean invoke = QuitHandler.invokeMessage("!vc leave");
        assertTrue(invoke);
    }

    @Test
    public void testQuitInvokeConditionFalse() {
        boolean invoke = QuitHandler.invokeMessage("leave");
        assertFalse(invoke);
    }
}
