package usecase.pull_remote_hand;

/**
 * Input data for the pull hand from discord use case.
 *
 * This class stores the player name as input for the "pull hand"
 * use case, which is typically used when interacting with the
 * Discord bot.
 */
public class PullRemoteHandInputData {

    /**
     * The name of the player associated with the pull hand data.
     */
    private final String playerName;

    /**
     * Constructor for creating an instance of PullRemoteHandInputData.
     *
     * @param playerNameInput the name of the player to associate with the
     *                   pull hand data
     */
    public PullRemoteHandInputData(final String playerNameInput) {
        this.playerName = playerNameInput;
    }

    /**
     * Returns the player name associated with the pull hand data.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }
}
