package unit.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import entity.CallGroup;
import entity.PlayerStats;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerStatsTest {
  PlayerStats playerStats;

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
  BaseTile selfWind = BaseTile.windTon;
  BaseTile prevalentWind = BaseTile.windShaa;
  List<Tile> hand;
  List<BaseTile> baseHand;
  List<CallGroup> callGroups;
  private int numAkadora = 1;

  @BeforeEach
  void setUp() {
    hand = new ArrayList<>();
    baseHand = new ArrayList<>();
    callGroups = new ArrayList<>();

    hand.add(new Tile(BaseTile._2z));
    hand.add(new Tile(BaseTile._3p));
    baseHand.add(BaseTile._2z);
    baseHand.add(BaseTile._3p);
    playerStats = new PlayerStats(
        isRiichi,
        isDoubleRiichi,
        isIppatsu,
        isMenzenchin,
        isTsumo,
        isRinshan,
        isHaitei,
        isHoutei,
        isChankan,
        isTenhou,
        isChiihou,
        isOya,
        selfWind,
        prevalentWind,
        hand,
        callGroups,
        numAkadora);
  }

  @Test
  void testIsRiichi() {
    assertEquals(isRiichi, playerStats.isRiichi());
  }

  @Test
  void testSetRiichi() {
    playerStats.setRiichi(false);
    assertEquals(false, playerStats.isRiichi());
  }

  @Test
  void testIsDoubleRiichi() {
    assertEquals(isDoubleRiichi, playerStats.isDoubleRiichi());
  }

  @Test
  void testSetDoubleRiichi() {
    playerStats.setDoubleRiichi(true);
    assertEquals(true, playerStats.isDoubleRiichi());
  }

  @Test
  void testIsIppatsu() {
    assertEquals(isIppatsu, playerStats.isIppatsu());
  }

  @Test
  void testSetIppatsu() {
    playerStats.setIppatsu(false);
    assertEquals(false, playerStats.isIppatsu());
  }

  @Test
  void testIsMenzenchin() {
    assertEquals(isMenzenchin, playerStats.isMenzenchin());
  }

  @Test
  void testSetMenzenchin() {
    playerStats.setMenzenchin(false);
    assertEquals(false, playerStats.isMenzenchin());
  }

  @Test
  void testIsTsumo() {
    assertEquals(isTsumo, playerStats.isTsumo());
  }

  @Test
  void testSetTsumo() {
    playerStats.setTsumo(true);
    assertEquals(true, playerStats.isTsumo());
  }

  @Test
  void testIsRinshan() {
    assertEquals(isRinshan, playerStats.isRinshan());
  }

  @Test
  void testSetRinshan() {
    playerStats.setRinshan(false);
    assertEquals(false, playerStats.isRinshan());
  }

  @Test
  void testIsHaitei() {
    assertEquals(isHaitei, playerStats.isHaitei());
  }

  @Test
  void testSetHaitei() {
    playerStats.setHaitei(true);
    assertEquals(true, playerStats.isHaitei());
  }

  @Test
  void testIsHoutei() {
    assertEquals(isHoutei, playerStats.isHoutei());
  }

  @Test
  void testSetHoutei() {
    playerStats.setHoutei(false);
    assertEquals(false, playerStats.isHoutei());
  }

  @Test
  void testIsChankan() {
    assertEquals(isChankan, playerStats.isChankan());
  }

  @Test
  void testSetChankan() {
    playerStats.setChankan(true);
    assertEquals(true, playerStats.isChankan());
  }

  @Test
  void testIsTenhou() {
    assertEquals(isTenhou, playerStats.isTenhou());
  }

  @Test
  void testSetTenhou() {
    playerStats.setTenhou(false);
    assertEquals(false, playerStats.isTenhou());
  }

  @Test
  void testIsChiihou() {
    assertEquals(isChiihou, playerStats.isChiihou());
  }

  @Test
  void testSetChiihou() {
    playerStats.setChiihou(true);
    assertEquals(true, playerStats.isChiihou());
  }

  @Test
  void testGetHand() {
    assertEquals(hand.size(), playerStats.getHand().size());
  }

  @Test
  void testSetHand() {
    List<Tile> newHand = new ArrayList<>();
    newHand.add(new Tile(BaseTile._1z));
    playerStats.setHand(newHand);
    assertEquals(newHand.size(), playerStats.getHand().size());
  }

  @Test
  void testIsOya() {
    assertEquals(isOya, playerStats.isOya());
  }

  @Test
  void testSetOya() {
    playerStats.setOya(false);
    assertFalse(playerStats.isOya());
  }

  @Test
  void testGetBaseHands() {
    assertEquals(baseHand.size(), playerStats.getBaseHands().size());
  }

  @Test
  void testGetSelfWind() {
    assertEquals(selfWind, playerStats.getSelfWind());
  }

  @Test
  void testSetSelfWind() {
    BaseTile newSelfWind = BaseTile.windNan;
    playerStats.setSelfWind(newSelfWind);
    assertEquals(newSelfWind, playerStats.getSelfWind());
  }

  @Test
  void testGetPrevalentWind() {
    assertEquals(prevalentWind, playerStats.getPrevalentWind());
  }

  @Test
  void testSetPrevalentWind() {
    BaseTile newPrevalentWind = BaseTile.windPei;
    playerStats.setPrevalentWind(newPrevalentWind);
    assertEquals(newPrevalentWind, playerStats.getPrevalentWind());
  }

  @Test
  void testGetBaseHand() {
    assertEquals(baseHand.size(), playerStats.getBaseHand().size());
  }

  @Test
  void testSetBaseHand() {
    List<BaseTile> newBaseHand = new ArrayList<>();
    newBaseHand.add(BaseTile._1z);
    playerStats.setBaseHand(newBaseHand);
    assertEquals(newBaseHand.size(), playerStats.getBaseHand().size());
  }

  @Test
  void testGetCallGroups() {
    assertEquals(callGroups.size(), playerStats.getCallGroups().size());
  }

  @Test
  void testSetCallGroups() {
    List<CallGroup> newCallGroups = new ArrayList<>();
    playerStats.setCallGroups(newCallGroups);
    assertEquals(newCallGroups.size(), playerStats.getCallGroups().size());
  }

  @Test
  void testGetNumAkadora() {
    assertEquals(numAkadora, playerStats.getNumAkaDora());
  }

  @Test
  void testSetNumAkadora() {
    int newNumAkadora = 2;
    playerStats.setNumAkaDora(newNumAkadora);
    assertEquals(newNumAkadora, playerStats.getNumAkaDora());
  }
}
