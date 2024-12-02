package unit.usecase.editstatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usecase.editstatus.EditStatusOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Test class to cover untested parts of EditStatusOutputData.
 */
public class EditStatusOutputDataTest {
  private String playerName;

  private String winType;
  private String roundWind;
  private String seatWind;
  private int numAkadora;
  private Boolean[] attributes;

  private boolean useCaseFailed;

  private EditStatusOutputData outputData;

  @BeforeEach
  void setUp() {
    attributes = new Boolean[] { false, true, false, false, false, false, true, false, false };
    numAkadora = 0;
    seatWind = "East";
    roundWind = "East";
    winType = "Ron";
    playerName = "default";

    outputData = new EditStatusOutputData(attributes, numAkadora, seatWind, roundWind, winType, playerName);
  }

  @Test
  void testGetPlayerName() {
    // Call the method
    String result = outputData.getPlayerName();

    // Check whether the result is correct
    assertEquals(playerName, result);
  }

  @Test
  void testGetWinType() {
    // Call the method
    String result = outputData.getWinType();

    // Check whether the result is correct
    assertEquals(winType, result);
  }

  @Test
  void testGetRoundWind() {
    // Call the method
    String result = outputData.getRoundWind();

    // Check whether the result is correct
    assertEquals(roundWind, result);
  }

  @Test
  void testGetSeatWind() {
    // Call the method
    String result = outputData.getSeatWind();

    // Check whether the result is correct
    assertEquals(seatWind, result);
  }

  @Test
  void testGetNumAkadora() {
    // Call the method
    int result = outputData.getNumAkadora();

    // Check whether the result is correct
    assertEquals(numAkadora, result);
  }

  @Test
  void testGetAttributes() {
    // Call the method
    Boolean[] result = outputData.getAttributes();

    // Check whether the result is correct
    assertEquals(attributes, result);
  }

  @Test
  void testIsUseCaseFailed() {
    // Call the method
    boolean result = outputData.isUseCaseFailed();

    // Check whether the result is correct
    assertEquals(useCaseFailed, result);
  }

  @Test
  void testGetDoraCounts() {
    // Call the method
    Boolean[][] result = outputData.getDoraCounts();

    // Check whether the result is correct
    assertNull(result);
  }
}
