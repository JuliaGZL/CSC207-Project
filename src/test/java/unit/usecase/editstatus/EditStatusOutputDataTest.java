/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package unit.usecase.editstatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usecase.editstatus.EditStatusOutputData;

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
