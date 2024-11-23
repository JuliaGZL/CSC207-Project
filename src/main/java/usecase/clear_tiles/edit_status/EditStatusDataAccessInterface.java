package usecase.clear_tiles.edit_status;

import entity.Player;

/**
 * The interface of the DAO for the Edit Status Use Case.
 */
public interface EditStatusDataAccessInterface {

    /**
     * Updates the system to record this player's gameplay attributes.
     * @param player the player whose gameplay attributes are to be updated
     */
    void editStatus(Player player);

    /**
     * Updates the system to record this user's red dora states.
     * @param player the player whose red dora states are to be updated
     */
    void editRedDora(Player player);

    /**
     * Updates the system to record this user's dora tiles.
     * @param player the player whose dora tiles are to be updated
     */
    void editDora(Player player);
}
