package mahjong;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the state of a player in a Mahjong game.
 */
public class PlayerStats {
  private boolean isRiichi;
  private boolean isDoubleRiichi;
  private boolean isIppatsu;
  private boolean isMenzenchin;
  private boolean isTsumo;
  private boolean isRinshan;
  private boolean isHaitei;
  private boolean isHoutei;
  private boolean isChankan;
  private boolean isTenhou;
  private boolean isChiihou;
  private boolean isOya;
  private List<Tile> hand;
  private List<BaseTile> baseHand;

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
   * @param isDoubleRiichi true to set the player in Riichi state, false otherwise.
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
  public Tile[] getHand() {
    return hand;
  }

  /**
   * Sets the hand of the player.
   * 
   * @param hand an array of Tile to set as the player's hand.
   */
  public void setHand(Tile[] hand) {
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

  public List<BaseTile> getBaseHands() {
    return baseHand;
  }

  /**
   * Constructs a PlayerState with the specified states and hand.
   * 
   * @param isRiichi      whether the player is in Riichi state.
   * @param isIppatsu    whether the player is in Ippatsu state.
   * @param isMenzenchin whether the player is in Menzenchin state.
   * @param isTsumo      whether the player is in Tsumo state.
   * @param isRinshan    whether the player is in Rinshan state.
   * @param isHaitei     whether the player is in Haitei state.
   * @param isHoutei     whether the player is in Houtei state.
   * @param isChankan    whether the player is in Chankan state.
   * @param isTenhou     whether the player is in Tenhou state.
   * @param isChiihou    whether the player is in Chiihou state.
   * @param isOya        whether the player is in Oya state.
   * @param hand         the hand of the player.
   */
  public PlayerStats(
      boolean isRiichi, boolean isIppatsu, boolean isMenzenchin,
      boolean isTsumo, boolean isRinshan, boolean isHaitei,
      boolean isHoutei, boolean isChankan, boolean isTenhou,
      boolean isChiihou, boolean isOya, List<Tile> hand) {
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
    this.hand = hand;
    this.baseHand = new ArrayList<>();
    for (Tile tile : hand) {
      baseHand.add(tile.getBaseTile());
    }
  }
}
