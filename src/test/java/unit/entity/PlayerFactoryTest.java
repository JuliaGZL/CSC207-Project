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
import entity.PlayerFactory;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for PlayerFactory.
 */
public class PlayerFactoryTest {
  PlayerFactory playerFactory;

  @BeforeEach
  void setUp() {
    playerFactory = new PlayerFactory();
  }

  @Test
  void testCreate() {
    String name = "test";
    int score = 0;
    List<BaseTile> hand = new ArrayList<>();
    hand.add(BaseTile._1m);
    List<BaseTile> dora = new ArrayList<>();
    dora.add(BaseTile._2m);
    List<BaseTile> uradora = new ArrayList<>();
    uradora.add(BaseTile._3m);

    Player player = playerFactory.create(name, score, hand, dora, uradora);

    assertEquals(name, player.getName());
    assertEquals(score, player.getScore());
    assertEquals(hand.size() + dora.size() +
        uradora.size(), player.getHand().size());
    assertEquals(dora.size(), player.getDora().size());
    assertEquals(uradora.size(), player.getUradora().size());
    assertEquals(hand.get(0), player.getHand().get(0).getBaseTile());
    // assertEquals(dora, player.getDora());
  }

  @Test
  void testCreateEmpty() {
    String name = "test";

    Player player = playerFactory.createEmpty(name);

    assertEquals(name, player.getName());
    assertEquals(0, player.getScore());
    assertEquals(0, player.getHand().size());
    assertEquals(0, player.getDora().size());
    assertEquals(0, player.getUradora().size());
  }
}
