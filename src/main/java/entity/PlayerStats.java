package entity;

import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;

/**
 * Represents the state of a player in a Mahjong game.
 */
public class PlayerStats {
  private boolean isRiichi;
  private boolean isDoubleRiichi;
  private boolean isIppatsu; // One Shot
  private boolean isMenzenchin;
  private boolean isTsumo;
  private boolean isRinshan; // After a Kan
  private boolean isHaitei; // Under the Sea
  private boolean isHoutei; // Under the River
  private boolean isChankan; // Robbing a Kan
  private boolean isTenhou;
  private boolean isChiihou;
  private boolean isOya;
  private BaseTile selfWind;
  private BaseTile prevalentWind;
  private List<Tile> hand;
  private List<BaseTile> baseHand;
  private List<CallGroup> callGroups;
  private int numAkaDora;

  /**
   * Checks if the player is in Riichi state.
   * 
   * @return true if the player is in Riichi state, false otherwise.
   */
  public boolean isRiichi() {
    return isRiichi;
  }

  /**
   * Sets the Riichi state of the player.
   * 
   * @param isRiichi true to set the player in Riichi state, false otherwise.
   */
  public void setRiichi(boolean isRiichi) {
    this.isRiichi = isRiichi;
  }

  /**
   * Checks if the player is in Double Riichi state.
   * 
   * @return true if the player is in Double Riichi state, false otherwise.
   */
  public boolean isDoubleRiichi() {
    return isDoubleRiichi;
  }

  /**
   * Sets the Double Richi state of the player.
   * 
   * @param isDoubleRiichi true to set the player in Riichi state, false
   *                       otherwise.
   */
  public void setDoubleRiichi(boolean isDoubleRiichi) {
    this.isDoubleRiichi = isDoubleRiichi;
  }

  /**
   * Checks if the player is in Ippatsu state.
   * 
   * @return true if the player is in Ippatsu state, false otherwise.
   */
  public boolean isIppatsu() {
    return isIppatsu;
  }

  /**
   * Sets the Ippatsu state of the player.
   * 
   * @param isIppatsu true to set the player in Ippatsu state, false otherwise.
   */
  public void setIppatsu(boolean isIppatsu) {
    this.isIppatsu = isIppatsu;
  }

  /**
   * Checks if the player is in Menzenchin state.
   * 
   * @return true if the player is in Menzenchin state, false otherwise.
   */
  public boolean isMenzenchin() {
    return isMenzenchin;
  }

  /**
   * Sets the Menzenchin state of the player.
   * 
   * @param isMenzenchin true to set the player in Menzenchin state, false
   *                     otherwise.
   */
  public void setMenzenchin(boolean isMenzenchin) {
    this.isMenzenchin = isMenzenchin;
  }

  /**
   * Checks if the player is in Tsumo state.
   * 
   * @return true if the player is in Tsumo state, false otherwise.
   */
  public boolean isTsumo() {
    return isTsumo;
  }

  /**
   * Sets the Tsumo state of the player.
   * 
   * @param isTsumo true to set the player in Tsumo state, false otherwise.
   */
  public void setTsumo(boolean isTsumo) {
    this.isTsumo = isTsumo;
  }

  /**
   * Checks if the player is in Rinshan state.
   * 
   * @return true if the player is in Rinshan state, false otherwise.
   */
  public boolean isRinshan() {
    return isRinshan;
  }

  /**
   * Sets the Rinshan state of the player.
   * 
   * @param isRinshan true to set the player in Rinshan state, false otherwise.
   */
  public void setRinshan(boolean isRinshan) {
    this.isRinshan = isRinshan;
  }

  /**
   * Checks if the player is in Haitei state.
   * 
   * @return true if the player is in Haitei state, false otherwise.
   */
  public boolean isHaitei() {
    return isHaitei;
  }

  /**
   * Sets the Haitei state of the player.
   * 
   * @param isHaitei true to set the player in Haitei state, false otherwise.
   */
  public void setHaitei(boolean isHaitei) {
    this.isHaitei = isHaitei;
  }

  /**
   * Checks if the player is in Houtei state.
   * 
   * @return true if the player is in Houtei state, false otherwise.
   */
  public boolean isHoutei() {
    return isHoutei;
  }

  /**
   * Sets the Houtei state of the player.
   * 
   * @param isHoutei true to set the player in Houtei state, false otherwise.
   */
  public void setHoutei(boolean isHoutei) {
    this.isHoutei = isHoutei;
  }

  /**
   * Checks if the player is in Chankan state.
   * 
   * @return true if the player is in Chankan state, false otherwise.
   */
  public boolean isChankan() {
    return isChankan;
  }

  /**
   * Sets the Chankan state of the player.
   * 
   * @param isChankan true to set the player in Chankan state, false otherwise.
   */
  public void setChankan(boolean isChankan) {
    this.isChankan = isChankan;
  }

  /**
   * Checks if the player is in Tenhou state.
   * 
   * @return true if the player is in Tenhou state, false otherwise.
   */
  public boolean isTenhou() {
    return isTenhou;
  }

  /**
   * Sets the Tenhou state of the player.
   * 
   * @param isTenhou true to set the player in Tenhou state, false otherwise.
   */
  public void setTenhou(boolean isTenhou) {
    this.isTenhou = isTenhou;
  }

  /**
   * Checks if the player is in Chiihou state.
   * 
   * @return true if the player is in Chiihou state, false otherwise.
   */
  public boolean isChiihou() {
    return isChiihou;
  }

  /**
   * Sets the Chiihou state of the player.
   * 
   * @param isChiihou true to set the player in Chiihou state, false otherwise.
   */
  public void setChiihou(boolean isChiihou) {
    this.isChiihou = isChiihou;
  }

  /**
   * Gets the hand of the player.
   * 
   * @return an array of Tile representing the player's hand.
   */
  public List<Tile> getHand() {
    return hand;
  }

  /**
   * Sets the hand of the player in Tile.
   * 
   * @param hand an array of Tile to set as the player's hand.
   */
  public void setHand(List<Tile> hand) {
    this.hand = hand;
  }

  /**
   * Checks if the player is the dealer (Oya).
   * 
   * @return true if the player is the dealer, false otherwise.
   */
  public boolean isOya() {
    return isOya;
  }

  /**
   * Sets the dealer (Oya) state of the player.
   * 
   * @param isOya true to set the player as the dealer, false otherwise.
   */
  public void setOya(boolean isOya) {
    this.isOya = isOya;
  }

  /**
   * Gets the hand of the player in BaseTile.
   *
   * @return an array of BaseTile representing the player's hand.
   */
  public List<BaseTile> getBaseHands() {
    return baseHand;
  }

  /**
   * Retrieves the self wind tile of the player.
   *
   * @return the self wind tile of the player.
   */
  public BaseTile getSelfWind() {
    return selfWind;
  }

  /**
   * Set the self wind tile of the player.
   *
   * @param selfWind the self wind tile of the player.
   */
  public void setSelfWind(BaseTile selfWind) {
    this.selfWind = selfWind;
  }

  /**
   * Retrieves the prevalent wind tile of the player.
   *
   * @return the prevalent wind tile of the player.
   */
  public BaseTile getPrevalentWind() {
    return prevalentWind;
  }

  /**
   * Set the prevalent wind tile of the player.
   *
   * @param prevalentWind the prevalent wind tile of the player.
   */
  public void setPrevalentWind(BaseTile prevalentWind) {
    this.prevalentWind = prevalentWind;
  }

  /**
   * Retrieves the base hand of the player.
   *
   * @return a list of BaseTile objects representing the player's base hand.
   */
  public List<BaseTile> getBaseHand() {
    return baseHand;
  }

  /**
   * Sets the base hand for the player.
   *
   * @param baseHand the list of BaseTile objects representing the player's base
   *                 hand
   */
  public void setBaseHand(List<BaseTile> baseHand) {
    this.baseHand = baseHand;
  }

  /**
   * Retrieves the base hand of the player.
   *
   * @return a list of CallGroup objects representing the player's Fuuros.
   */
  public List<CallGroup> getCallGroups() {
    return callGroups;
  }

  /**
   * Sets the call groups for the player.
   *
   * @param callGroups the list of CallGroup objects representing the player's
   *                   Fuuros.
   */
  public void setCallGroups(List<CallGroup> callGroups) {
    this.callGroups = callGroups;
  }

  /**
   * Returns the number of red dora tiles (aka dora) the player has.
   *
   * @return the number of red dora tiles.
   */
  public int getNumAkaDora() {
    return numAkaDora;
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
   * Constructs a PlayerState with the specified states and hand.
   *
   * @param isRiichi      whether the player is in Riichi state.
   * @param isIppatsu     whether the player is in Ippatsu state.
   * @param isMenzenchin  whether the player is in Menzenchin state.
   * @param isTsumo       whether the player is in Tsumo state.
   * @param isRinshan     whether the player is in Rinshan state.
   * @param isHaitei      whether the player is in Haitei state.
   * @param isHoutei      whether the player is in Houtei state.
   * @param isChankan     whether the player is in Chankan state.
   * @param isTenhou      whether the player is in Tenhou state.
   * @param isChiihou     whether the player is in Chiihou state.
   * @param isOya         whether the player is in Oya state.
   * @param selfWind      the self wind tile of the player.
   * @param prevalentWind the prevalent wind tile of the player.
   * @param hand          the hand of the player.
   * @param callGroups    the call groups (Fuuros) of the player.
   */
  public PlayerStats(
      boolean isRiichi, boolean isDoubleRiichi, boolean isIppatsu,
      boolean isMenzenchin, boolean isTsumo, boolean isRinshan,
      boolean isHaitei, boolean isHoutei, boolean isChankan,
      boolean isTenhou, boolean isChiihou, boolean isOya, BaseTile selfWind,
      BaseTile prevalentWind, List<Tile> hand, List<CallGroup> callGroups,
      int numAkaDora) {
    this.isRiichi = isRiichi;
    this.isIppatsu = isIppatsu;
    this.isMenzenchin = isMenzenchin;
    this.isTsumo = isTsumo;
    this.isRinshan = isRinshan;
    this.isHaitei = isHaitei;
    this.isHoutei = isHoutei;
    this.isChankan = isChankan;
    this.isTenhou = isTenhou;
    this.isChiihou = isChiihou;
    this.isOya = isOya;
    this.selfWind = selfWind;
    this.prevalentWind = prevalentWind;
    this.hand = hand;
    this.callGroups = callGroups;
    this.baseHand = new ArrayList<>();
    for (Tile tile : hand) {
      baseHand.add(tile.getBaseTile());
    }
    this.numAkaDora = numAkaDora;
  }
}
