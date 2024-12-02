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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usecase.editstatus.EditStatusInputData;

/**
 * Test for EditStatusInputData.
 */
class EditStatusInputDataTest {
  String playerName = "Alice";
  String winType = "Ron";
  String roundWind = "South";
  String seatWind = "East";
  int numAkadora = 1;
  Boolean[] attributes = { false, true, false, true, false, false, false, false, false };

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
