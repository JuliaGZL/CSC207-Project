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

package entity;

import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.Rule;

/**
 * Builder class for constructing PlayerStats objects.
 */
public class PlayerStatsBuilder {
  private boolean isRiichi = false;
  private boolean isDoubleRiichi = false;
  private boolean isIppatsu = false;
  private boolean isMenzenchin = true;
  private boolean isTsumo = false;
  private boolean isRinshan = false;
  private boolean isHaitei = false;
  private boolean isHoutei = false;
  private boolean isChankan = false;
  private boolean isTenhou = false;
  private boolean isChiihou = false;
  private boolean isOya = false;
  private BaseTile selfWind = BaseTile.windTon;
  private BaseTile prevalentWind = BaseTile.windTon;
  private List<CallGroup> callGroups = new ArrayList<>();
  private List<Tile> hand;
  private List<Tile> doraList = new ArrayList<>();
  private List<Tile> uraDoraList = new ArrayList<>();
  private int numAkaDora = 0;

  /**
   * Sets the Riichi status.
   *
   * @param isRiichi true if the player declared Riichi, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setRiichi(boolean isRiichi) {
    this.isRiichi = isRiichi;
    return this;
  }

  /**
   * Sets the Double Riichi status.
   *
   * @param isDoubleRiichi true if the player declared Double Riichi, false
   *                       otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setDoubleRiichi(boolean isDoubleRiichi) {
    this.isDoubleRiichi = isDoubleRiichi;
    return this;
  }

  /**
   * Sets the Ippatsu status.
   *
   * @param isIppatsu true if the player won with Ippatsu, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setIppatsu(boolean isIppatsu) {
    this.isIppatsu = isIppatsu;
    return this;
  }

  /**
   * Sets the Menzenchin status.
   *
   * @param isMenzenchin true if the player has a closed hand, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setMenzenchin(boolean isMenzenchin) {
    this.isMenzenchin = isMenzenchin;
    return this;
  }

  /**
   * Sets the Tsumo status.
   *
   * @param isTsumo true if the player won by Tsumo, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setTsumo(boolean isTsumo) {
    this.isTsumo = isTsumo;
    return this;
  }

  /**
   * Sets the Rinshan status.
   *
   * @param isRinshan true if the player won with Rinshan, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setRinshan(boolean isRinshan) {
    this.isRinshan = isRinshan;
    return this;
  }

  /**
   * Sets the Haitei status.
   *
   * @param isHaitei true if the player won with Haitei, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setHaitei(boolean isHaitei) {
    this.isHaitei = isHaitei;
    return this;
  }

  /**
   * Sets the Houtei status.
   *
   * @param isHoutei true if the player won with Houtei, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setHoutei(boolean isHoutei) {
    this.isHoutei = isHoutei;
    return this;
  }

  /**
   * Sets the Chankan status.
   *
   * @param isChankan true if the player won with Chankan, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setChankan(boolean isChankan) {
    this.isChankan = isChankan;
    return this;
  }

  /**
   * Sets the Tenhou status.
   *
   * @param isTenhou true if the player won with Tenhou, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setTenhou(boolean isTenhou) {
    this.isTenhou = isTenhou;
    return this;
  }

  /**
   * Sets the Chiihou status.
   *
   * @param isChiihou true if the player won with Chiihou, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setChiihou(boolean isChiihou) {
    this.isChiihou = isChiihou;
    return this;
  }

  /**
   * Sets the Oya status.
   *
   * @param isOya true if the player is the dealer, false otherwise.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setOya(boolean isOya) {
    this.isOya = isOya;
    return this;
  }

  /**
   * Sets the player's self wind.
   *
   * @param selfWind the self wind tile.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setSelfWind(BaseTile selfWind) {
    this.selfWind = selfWind;
    return this;
  }

  /**
   * Sets the prevalent wind.
   *
   * @param prevalentWind the prevalent wind tile.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setPrevalentWind(BaseTile prevalentWind) {
    this.prevalentWind = prevalentWind;
    return this;
  }

  /**
   * Sets the player's call groups.
   *
   * @param callGroups the list of call groups.
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setCallGroups(List<CallGroup> callGroups) {
    this.callGroups = callGroups;
    return this;
  }

  /**
   * Sets the list of hand tiles for the player.
   *
   * @param hand the list of hand tiles to be set
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setHand(List<Tile> hand) {
    this.hand = hand;
    return this;
  }

  /**
   * Sets the list of Dora tiles for the player.
   *
   * @param doraList the list of Dora tiles to be set
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setDoraList(List<Tile> doraList) {
    this.doraList = doraList;
    return this;
  }

  /**
   * Sets the list of Dora tiles for the player.
   *
   * @param uraDoraList the list of Dora tiles to be set
   * @return the current instance of PlayerStatsBuilder.
   */
  public PlayerStatsBuilder setUraDoraList(List<Tile> uraDoraList) {
    this.uraDoraList = uraDoraList;
    return this;
  }

  /**
   * Sets the number of Aka Dora (red bonus tiles) for the player.
   *
   * @param numAkaDora the number of Aka Dora to set
   */
  public PlayerStatsBuilder setNumAkaDora(int numAkaDora) {
    this.numAkaDora = numAkaDora;
    return this;
  }

  /**
   * Builds and returns a PlayerStats object.
   *
   * @return a new PlayerStats object.
   */
  public PlayerStats build() {
    if (hand == null) {
      throw new IllegalArgumentException("ハンドは必要です！");
    }
    List<BaseTile> doras = Rule.getDoraList(doraList);
    List<BaseTile> uraDoras = Rule.getDoraList(uraDoraList);
    for (Tile tile : hand) {
      if (doras.contains(tile.getBaseTile())) {
        tile.setDora(true);
      }
      if (uraDoras.contains(tile.getBaseTile())) {
        tile.setUraDora(true);
      }
    }
    return new PlayerStats(
        isRiichi, isDoubleRiichi, isIppatsu, isMenzenchin,
        isTsumo, isRinshan, isHaitei,
        isHoutei, isChankan, isTenhou,
        isChiihou, isOya, selfWind,
        prevalentWind, hand, callGroups,
        numAkaDora);
  }
}