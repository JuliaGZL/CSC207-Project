package unit.use_case.edit_status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.edit_status.EditStatusInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Test for EditStatusInputData.
 */
class EditStatusInputDataTest {
    String playerName = "Alice";
    String winType = "Ron";
    String roundWind = "South";
    String seatWind = "East";
    int numAkadora = 1;
    Boolean[] attributes = { false, true, false, true, false, false, false, false, false};

    EditStatusInputData editStatusInputData;

    @BeforeEach
    public void setUp() {
        editStatusInputData = new EditStatusInputData(attributes,
                numAkadora, seatWind, roundWind, winType, playerName);
    }

    @Test
    void getPlayerNameTest() {
        assertEquals(playerName, editStatusInputData.getPlayerName());
    }

    @Test
    void getWinTypeTest() {
        assertEquals(winType, editStatusInputData.getWinType());
    }

    @Test
    void getRoundWindTest() {
        assertEquals(roundWind, editStatusInputData.getRoundWind());
    }

    @Test
    void getSeatWindTest() {
        assertEquals(seatWind, editStatusInputData.getSeatWind());
    }

    @Test
    void getNumAkadoraTest() {
        assertEquals(numAkadora, editStatusInputData.getNumAkadora());
    }

    @Test
    void getAttributesTest() {
        assertArrayEquals(attributes, editStatusInputData.getAttributes());
    }
}
