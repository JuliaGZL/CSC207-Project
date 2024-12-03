package usecase.pull_remote_hand;

import mahjong.BaseTile;

import java.util.List;

/**
 * Output data for the pull hand from discord use case.
 *
 * This class stores the data output for the pull hand use case, including
 * player information, the player's hand, and lists for names and icons.
 */
public class PullRemoteHandOutputData {

    /**
     * The name of the player associated with this hand data.
     */
    private final String playerName;

    /**
     * The list of BaseTile objects representing the player's hand.
     */
    private final List<BaseTile> playerHand;

    /**
     * The list of player names participating in the pull hand use case.
     */
    private final List<String> nameList;

    /**
     * The list of player icons associated with the participants.
     */
    private final List<String> iconList;

    /**
     * Constructor to initialize PullRemoteHandOutputData with the necessary
     * data for the pull hand use case.
     *
     * @param playerNameIpt the name of the player
     * @param playerHandIpt the player's hand as a list of BaseTile objects
     * @param nameListIpt   the list of player names
     * @param iconListIpt   the list of player icons
     */
    public PullRemoteHandOutputData(final String playerNameIpt,
                                    final List<BaseTile> playerHandIpt,
                                    final List<String> nameListIpt,
                                    final List<String> iconListIpt) {
        this.playerName = playerNameIpt;
        this.playerHand = playerHandIpt;
        this.nameList = nameListIpt;
        this.iconList = iconListIpt;
    }

    /**
     * Retrieves the name of the player associated with this hand data.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Retrieves the player's hand as a list of BaseTile objects.
     *
     * @return the player's hand
     */
    public List<BaseTile> getPlayerHand() {
        return playerHand;
    }

    /**
     * Retrieves the list of player names participating in the pull hand
     * use case.
     *
     * @return the list of player names
     */
    public List<String> getNameList() {
        return nameList;
    }

    /**
     * Retrieves the list of player icons associated with the participants.
     *
     * @return the list of player icons
     */
    public List<String> getIconList() {
        return iconList;
    }
}
