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

import entity.CallGroup;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for CallGroup class.
 */
public class CallGroupTest {
  CallGroup callGroupOneVar;
  CallGroup callGroupThreeVar;

  @BeforeEach
  void setUp() {
    callGroupOneVar = new CallGroup(CallGroup.Type.CHI);

    List<Tile> mockTiles = new ArrayList<>();
    mockTiles.add(new Tile(BaseTile._1m));
    mockTiles.add(new Tile(BaseTile._5z));
    callGroupThreeVar = new CallGroup(CallGroup.Type.PON, mockTiles, 0);
  }

  @Test
  void testGetTiles() {
    List<Tile> expectedTiles = new ArrayList<>();
    expectedTiles.add(new Tile(BaseTile._1m));
    expectedTiles.add(new Tile(BaseTile._5z));
    assertEquals(expectedTiles.size(), callGroupThreeVar.getTiles().size());
  }

  @Test
  void testGetTake() {
    assertEquals(0, callGroupThreeVar.getTake());
  }

  @Test
  void testGetType() {
    assertEquals(CallGroup.Type.CHI, callGroupOneVar.getType());
  }

  @Test
  void testSetTiles() {
    List<Tile> newTiles = new ArrayList<>();
    newTiles.add(new Tile(BaseTile._2m));
    newTiles.add(new Tile(BaseTile._6z));
    callGroupThreeVar.setTiles(newTiles);
    assertEquals(newTiles.size(), callGroupThreeVar.getTiles().size());
  }

  @Test
  void testSetTake() {
    callGroupThreeVar.setTake(1);
    assertEquals(1, callGroupThreeVar.getTake());
  }

  @Test
  void testSetType() {
    callGroupOneVar.setType(CallGroup.Type.PON);
    assertEquals(CallGroup.Type.PON, callGroupOneVar.getType());
  }

  @Test
  void testToStringChi() {
    // Create a call group with type CHI
    List<Tile> chiTiles = new ArrayList<>();
    chiTiles.add(new Tile(BaseTile._1m));
    chiTiles.add(new Tile(BaseTile._2m));
    chiTiles.add(new Tile(BaseTile._3m));
    CallGroup callGroup = new CallGroup(CallGroup.Type.CHI, chiTiles, 0);

    assertEquals(
        "(Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false])Tile [tile=2m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=3m, isRedDora=false, isUraDora=false, isDora=false]",
        callGroup.toString());
  }

  @Test
  void testToStringPon() {
    List<Tile> ponTiles = new ArrayList<>();
    ponTiles.add(new Tile(BaseTile._1m));
    ponTiles.add(new Tile(BaseTile._1m));
    ponTiles.add(new Tile(BaseTile._1m));
    CallGroup callGroup = new CallGroup(CallGroup.Type.PON, ponTiles, 0);

    assertEquals(
        "(Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false])Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]",
        callGroup.toString());
  }

  @Test
  void testToStringDaiminkan() {
    List<Tile> daiminkanTiles = new ArrayList<>();
    daiminkanTiles.add(new Tile(BaseTile._1m));
    daiminkanTiles.add(new Tile(BaseTile._1m));
    daiminkanTiles.add(new Tile(BaseTile._1m));
    CallGroup callGroup = new CallGroup(CallGroup.Type.DAIMINKAN, daiminkanTiles, 0);

    assertEquals(
        "(Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false])Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]",
        callGroup.toString());
  }

  @Test
  void testToStringKakan() {
    List<Tile> kakanTiles = new ArrayList<>();
    kakanTiles.add(new Tile(BaseTile._1m));
    kakanTiles.add(new Tile(BaseTile._1m));
    kakanTiles.add(new Tile(BaseTile._1m));
    kakanTiles.add(new Tile(BaseTile._1m));
    CallGroup callGroup = new CallGroup(CallGroup.Type.KAKAN, kakanTiles, 0);

    assertEquals(
        "Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]",
        callGroup.toString());
  }

  @Test
  void testToStringAnkan() {
    List<Tile> ankanTiles = new ArrayList<>();
    ankanTiles.add(new Tile(BaseTile._1m));
    ankanTiles.add(new Tile(BaseTile._1m));
    ankanTiles.add(new Tile(BaseTile._1m));
    ankanTiles.add(new Tile(BaseTile._1m));
    CallGroup callGroup = new CallGroup(CallGroup.Type.ANKAN, ankanTiles, 0);

    assertEquals(
        "Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]**Tile [tile=1m, isRedDora=false, isUraDora=false, isDora=false]",
        callGroup.toString());
  }

  // @Test
  // void testToStringFail() {
  // List<Tile> failTiles = new ArrayList<>();
  // failTiles.add(new Tile(BaseTile._1m));
  // failTiles.add(new Tile(BaseTile._1m));
  // CallGroup callGroup = new CallGroup(CallGroup.Type.PON, failTiles, 0);
  // callGroup.setType(null);
  //
  // RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
  // callGroup::toString);
  //
  // assertEquals("Bad call group (fuuro) type.", exception.getMessage());
  // }
}