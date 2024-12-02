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

package unit.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  String name = "default";
  int score = 0;
  List<Tile> hand = new ArrayList<>();
  List<Tile> dora = new ArrayList<>();
  List<Tile> uradora = new ArrayList<>();
  Boolean[] attributes = {
      false, false, false,
      false, false, false,
      false, false, false
  };
  int numAkaDora = 0;
  String winType = "Tsumo";
  String roundWind = "East";
  String seatWind = "East";

  Player playerOneVar;
  Player playerFiveVar;
  Player playerSevenVar;

  @BeforeEach
  void setUp() {
    playerOneVar = new Player(name);
    playerFiveVar = new Player(name, score, hand, dora, uradora);
    playerSevenVar = new Player(name, score, hand, attributes, numAkaDora, roundWind, seatWind);
  }

  @Test
  void testGetName() {
    assertEquals(name, playerOneVar.getName());
    assertEquals(name, playerFiveVar.getName());
    assertEquals(name, playerSevenVar.getName());
  }

  @Test
  void testGetScore() {
    assertEquals(score, playerFiveVar.getScore());
  }

  @Test
  void testGetHand() {
    assertEquals(hand, playerSevenVar.getHand());
  }

  @Test
  void testGetAttributes() {
    assertEquals(attributes, playerSevenVar.getAttributes());
  }

  @Test
  void testGetNumAkaDora() {
    assertEquals(numAkaDora, playerSevenVar.getNumAkaDora());
  }

  @Test
  void testGetWinType() {
    assertEquals(winType, playerSevenVar.getWinType());
  }

  @Test
  void testGetRoundWind() {
    assertEquals(roundWind, playerSevenVar.getRoundWind());
  }

  @Test
  void testGetSeatWind() {
    assertEquals(seatWind, playerSevenVar.getSeatWind());
  }

  @Test
  void testSetScore() {
    int newScore = 1000;
    playerFiveVar.setScore(newScore);
    assertEquals(newScore, playerFiveVar.getScore());
  }

  @Test
  void testSetHand() {
    List<Tile> newHand = new ArrayList<>();
    playerSevenVar.setHand(newHand);
    assertEquals(newHand, playerSevenVar.getHand());
  }

  @Test
  void testGetDora() {
    assertEquals(dora, playerFiveVar.getDora());
  }

  @Test
  void testSetDora() {
    List<Tile> newDora = new ArrayList<>();
    playerFiveVar.setDora(newDora);
    assertEquals(newDora, playerFiveVar.getDora());
  }

  @Test
  void testGetUradora() {
    assertEquals(uradora, playerFiveVar.getUradora());
  }

  @Test
  void testSetUradora() {
    List<Tile> newUradora = new ArrayList<>();
    playerFiveVar.setUradora(newUradora);
    assertEquals(newUradora, playerFiveVar.getUradora());
  }

  @Test
  void testSetAttributes() {
    Boolean[] newAttributes = {
        true, true, true,
        true, true, true,
        true, true, true
    };
    playerSevenVar.setAttributes(newAttributes);
    assertEquals(newAttributes, playerSevenVar.getAttributes());
  }

  @Test
  void testSetWinType() {
    String newWinType = "Ron";
    playerSevenVar.setWinType(newWinType);
    assertEquals(newWinType, playerSevenVar.getWinType());
  }

  @Test
  void testSetNumAkaDora() {
    int newNumAkaDora = 1;
    playerSevenVar.setNumAkaDora(newNumAkaDora);
    assertEquals(newNumAkaDora, playerSevenVar.getNumAkaDora());
  }

  @Test
  void testSetRoundWind() {
    String newRoundWind = "South";
    playerSevenVar.setRoundWind(newRoundWind);
    assertEquals(newRoundWind, playerSevenVar.getRoundWind());
  }

  @Test
  void tesetSetSeatWind() {
    String newSeatWind = "South";
    playerSevenVar.setSeatWind(newSeatWind);
    assertEquals(newSeatWind, playerSevenVar.getSeatWind());
  }
}
