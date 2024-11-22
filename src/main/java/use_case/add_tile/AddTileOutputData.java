package use_case.add_tile;

import java.util.List;

import mahjong.mahjong.BaseTile;

/**
 * Output data format for the add_tile use case.
 */
public class AddTileOutputData {
    private final boolean failed;
    private final String playerName;
    private final List<BaseTile> idList;
    private final List<String> nameList;
    private final List<String> iconList;

    public AddTileOutputData(
            boolean failed, String playerName, List<BaseTile> idList, List<String> nameList, List<String> iconList) {
        this.failed = failed;
        this.playerName = playerName;
        this.idList = idList;
        this.nameList = nameList;
        this.iconList = iconList;
    }

    public boolean isFailed() {
        return this.failed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<BaseTile> getIdList() {
        return idList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<String> getIconList() {
        return iconList;
    }
}
