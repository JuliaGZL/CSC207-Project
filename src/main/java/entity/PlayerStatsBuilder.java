package entity;

import java.util.ArrayList;
import java.util.List;

import mahjong.BaseTile;

/**
 * Builder class for constructing PlayerStats objects.
 */
public class PlayerStatsBuilder {
  private boolean isRiichi = false;
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
   * Sets the list of Dora tiles for the player.
   *
   * @param doraList the list of Dora tiles to be set
   */
  public void setDoraList(List<Tile> doraList) {
    this.doraList = doraList;
  }

  /**
   * Sets the list of Dora tiles for the player.
   *
   * @param uraDoraList the list of Dora tiles to be set
   */
  public void setUraDoraList(List<Tile> uraDoraList) {
    this.uraDoraList = uraDoraList;
  }

  /**
   * Sets the number of Aka Dora (red bonus tiles) for the player.
   *
   * @param numAkaDora the number of Aka Dora to set
   */
  public void setNumAkaDora(int numAkaDora) {
    this.numAkaDora = numAkaDora;
  }

  /**
   * Builds and returns a PlayerStats object.
   * 
   * @param hand the list of tiles in the player's hand.
   * @return a new PlayerStats object.
   */
  public PlayerStats build(List<Tile> hand) {
    return new PlayerStats(
        isRiichi, isIppatsu, isMenzenchin,
        isTsumo, isRinshan, isHaitei,
        isHoutei, isChankan, isTenhou,
        isChiihou, isOya, selfWind,
        prevalentWind, hand, callGroups, 
        numAkaDora);
  }
}