package usecase.removetile;

import java.util.List;
import mahjong.BaseTile;

/**
 * Output data format for the remove_tile use case.
 */
public class RemoveTileOutputData {
  private final boolean failed;
  private final String playerName;
  private final List<BaseTile> idList;
  private final List<String> nameList;
  private final List<String> iconList;

  /**
   * Constructs a RemoveTileOutputData object.
   *
   * @param failed whether the operation failed
   * @param playerName the name of the player
   * @param idList the list of tile IDs
   * @param nameList the list of tile names
   * @param iconList the list of tile icons
   */
  public RemoveTileOutputData(
      boolean failed, String playerName,
      List<BaseTile> idList, List<String> nameList, List<String> iconList) {
    this.failed = failed;
    this.playerName = playerName;
    this.idList = idList;
    this.nameList = nameList;
    this.iconList = iconList;
  }

  /**
   * Returns whether the operation failed.
   *
   * @return true if the operation failed, false otherwise
   */
  public boolean isFailed() {
    return this.failed;
  }

  /**
   * Returns the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Returns the list of tile IDs.
   *
   * @return the list of tile IDs
   */
  public List<BaseTile> getIdList() {
    return idList;
  }

  /**
   * Returns the list of tile names.
   *
   * @return the list of tile names
   */
  public List<String> getNameList() {
    return nameList;
  }

  /**
   * Returns the list of tile icons.
   *
   * @return the list of tile icons
   */
  public List<String> getIconList() {
    return iconList;
  }
}
