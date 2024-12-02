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

import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Tile.
 */
public class TileTest {
  BaseTile tile = BaseTile._2s;
  boolean isRedDora = true;
  boolean isUraDora = false;
  boolean isDora = true;

  Tile testTile;

  @BeforeEach
  void setUp() {
    testTile = new Tile(tile, isRedDora, isUraDora, isDora);
  }

  @Test
  void testIsRedDora() {
    assertEquals(isRedDora, testTile.isRedDora());
  }

  @Test
  void testIsUraDora() {
    assertEquals(isUraDora, testTile.isUraDora());
  }

  @Test
  void testIsDora() {
    assertEquals(isDora, testTile.isDora());
  }

  @Test
  void testGetTile() {
    assertEquals(tile, testTile.getTile());
  }
}
