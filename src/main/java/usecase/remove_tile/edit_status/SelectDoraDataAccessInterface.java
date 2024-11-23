package usecase.remove_tile.edit_status;

import entity.Player;

/**
 * The interface of the DAO for the Select Dora Use Case.
 */
public interface SelectDoraDataAccessInterface {

    /**
     * Updates the system to record the player's selection of Dora indicators.
     * @param player the player whose selection of Dora indicators is to be updated
     */
    void selectDoraIndicators(Player player);
}
