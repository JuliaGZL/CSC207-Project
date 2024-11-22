package interface_adapter.edit_tiles;

import java.util.List;

import mahjong.mahjong.BaseTile;

/**
 * State for property change listening for tile list display panels (both hand and dora).
 */
public class TilesDisplayState {
    private List<BaseTile> idList;
    private List<String> nameList;
    private List<String> iconList;
    private String playerName;
    private String errorMsg;

    public TilesDisplayState() {

    }

    public TilesDisplayState(List<BaseTile> idList, List<String> nameList, List<String> iconList) {
        this.idList = idList;
        this.nameList = nameList;
        this.iconList = iconList;
    }

    public List<BaseTile> getIdList() {
        return idList;
    }

    public void setIdList(List<BaseTile> idList) {
        this.idList = idList;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public List<String> getIconList() {
        return iconList;
    }

    public void setIconList(List<String> iconList) {
        this.iconList = iconList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
