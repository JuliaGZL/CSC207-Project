package usecase.pull_remote_hand;

import mahjong.BaseTile;

import java.util.List;

/**
 * Output data for the pull hand from discord use case.
 */
public class PullRemoteHandOutputData {
    private final String playerName;
    private final List<BaseTile> playerHand;
    private final List<String> nameList;
    private final List<String> iconList;

    public PullRemoteHandOutputData(String playerName, List<BaseTile> playerHand,
            List<String> nameList,
            List<String> iconList) {
        this.playerName = playerName;
        this.playerHand = playerHand;
        this.nameList = nameList;
        this.iconList = iconList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<BaseTile> getPlayerHand() {
        return playerHand;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<String> getIconList() {
        return iconList;
    }
}
