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
import entity.PlayerStats;
import entity.PlayerStatsBuilder;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerStatsBuilderTest {
  PlayerStatsBuilder builder;

  boolean isRiichi = true;
  boolean isDoubleRiichi = false;
  boolean isIppatsu = true;
  boolean isMenzenchin = true;
  boolean isTsumo = false;
  boolean isRinshan = true;
  boolean isHaitei = false;
  boolean isHoutei = true;
  boolean isChankan = false;
  boolean isTenhou = true;
  boolean isChiihou = false;
  boolean isOya = true;

  List<Tile> hand;

  @BeforeEach
  void setUp() {
    List<Tile> hand = new ArrayList<>();
    builder = new PlayerStatsBuilder().setRiichi(isRiichi).setDoubleRiichi(isDoubleRiichi).setIppatsu(isIppatsu)
        .setMenzenchin(isMenzenchin).setTsumo(isTsumo).setRinshan(isRinshan).setHaitei(isHaitei)
        .setHoutei(isHoutei).setChankan(isChankan).setTenhou(isTenhou).setChiihou(isChiihou).setOya(isOya)
        .setHand(hand);

  }

  @Test
  void testSetRiichi() {
    builder.setRiichi(isRiichi);
    PlayerStats playerStats = builder.build();
    assertEquals(isRiichi, playerStats.isRiichi());
  }

  @Test
  void testSetDoubleRiichi() {
    builder.setDoubleRiichi(isDoubleRiichi);
    PlayerStats playerStats = builder.build();
    assertEquals(isDoubleRiichi, playerStats.isDoubleRiichi());
  }

  @Test
  void testSetIppatsu() {
    builder.setIppatsu(isIppatsu);
    PlayerStats playerStats = builder.build();
    assertEquals(isIppatsu, playerStats.isIppatsu());
  }

  @Test
  void testSetMenzenchin() {
    builder.setMenzenchin(isMenzenchin);
    PlayerStats playerStats = builder.build();
    assertEquals(isMenzenchin, playerStats.isMenzenchin());
  }

  @Test
  void testSetTsumo() {
    builder.setTsumo(isTsumo);
    PlayerStats playerStats = builder.build();
    assertEquals(isTsumo, playerStats.isTsumo());
  }

  @Test
  void testSetRinshan() {
    builder.setRinshan(isRinshan);
    PlayerStats playerStats = builder.build();
    assertEquals(isRinshan, playerStats.isRinshan());
  }

  @Test
  void testSetHaitei() {
    builder.setHaitei(isHaitei);
    PlayerStats playerStats = builder.build();
    assertEquals(isHaitei, playerStats.isHaitei());
  }

  @Test
  void testSetHoutei() {
    builder.setHoutei(isHoutei);
    PlayerStats playerStats = builder.build();
    assertEquals(isHoutei, playerStats.isHoutei());
  }

  @Test
  void testSetChankan() {
    builder.setChankan(isChankan);
    PlayerStats playerStats = builder.build();
    assertEquals(isChankan, playerStats.isChankan());
  }

  @Test
  void testSetTenhou() {
    builder.setTenhou(isTenhou);
    PlayerStats playerStats = builder.build();
    assertEquals(isTenhou, playerStats.isTenhou());
  }

  @Test
  void testSetChiihou() {
    builder.setChiihou(isChiihou);
    PlayerStats playerStats = builder.build();
    assertEquals(isChiihou, playerStats.isChiihou());
  }

  @Test
  void testSetOya() {
    builder.setOya(isOya);
    PlayerStats playerStats = builder.build();
    assertEquals(isOya, playerStats.isOya());
  }

  @Test
  void testSetSelfWind() {
    BaseTile selfWind = BaseTile._1z;
    builder.setSelfWind(selfWind);
    PlayerStats playerStats = builder.build();
    assertEquals(selfWind, playerStats.getSelfWind());
  }

  @Test
  void testSetPrevalentWind() {
    BaseTile prevalentWind = BaseTile._1z;
    builder.setPrevalentWind(prevalentWind);
    PlayerStats playerStats = builder.build();
    assertEquals(prevalentWind, playerStats.getPrevalentWind());
  }

  @Test
  void testSetCallGroups() {
    CallGroup callGroup = new CallGroup(CallGroup.Type.CHI);
    builder.setCallGroups(List.of(callGroup));
    PlayerStats playerStats = builder.build();
    assertEquals(List.of(callGroup), playerStats.getCallGroups());
  }

  @Test
  void testSetHand() {
    List<Tile> newHand = List.of(new Tile(BaseTile._1z));
    builder.setHand(newHand);
    PlayerStats playerStats = builder.build();
    assertEquals(newHand, playerStats.getHand());
  }

  @Test
  void testSetDoraList() {
    List<Tile> doraList = List.of(new Tile(BaseTile._1z));
    builder.setDoraList(doraList);
  }

  @Test
  void testSetUraDoraList() {
    List<Tile> uraDoraList = List.of(new Tile(BaseTile._1z));
    builder.setUraDoraList(uraDoraList);
  }

  @Test
  void testSetNumAkaDora() {
    int numAkaDora = 1;
    builder.setNumAkaDora(numAkaDora);
    PlayerStats playerStats = builder.build();
    assertEquals(numAkaDora, playerStats.getNumAkaDora());
  }

  @Test
  void testBuildSuccess() {
    PlayerStats playerStats = builder.build();
    assertEquals(isRiichi, playerStats.isRiichi());
    assertEquals(isDoubleRiichi, playerStats.isDoubleRiichi());
    assertEquals(isIppatsu, playerStats.isIppatsu());
    assertEquals(isMenzenchin, playerStats.isMenzenchin());
    assertEquals(isTsumo, playerStats.isTsumo());
    assertEquals(isRinshan, playerStats.isRinshan());
    assertEquals(isHaitei, playerStats.isHaitei());
    assertEquals(isHoutei, playerStats.isHoutei());
    assertEquals(isChankan, playerStats.isChankan());
    assertEquals(isTenhou, playerStats.isTenhou());
    assertEquals(isChiihou, playerStats.isChiihou());
    assertEquals(isOya, playerStats.isOya());
  }

  @Test
  void testBuildFail() {
    try {
      List<Tile> nullHand = null;
      builder.setHand(nullHand);
      builder.build();
    } catch (IllegalArgumentException e) {
      assertEquals("ハンドは必要です！", e.getMessage());
    }
  }
}
