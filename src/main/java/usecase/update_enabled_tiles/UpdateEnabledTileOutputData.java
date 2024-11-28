package usecase.update_enabled_tiles;

import mahjong.BaseTile;

import java.util.Set;

/**
 * Output data structure for update enabled tile use case.
 */
public class UpdateEnabledTileOutputData {
    private final Set<BaseTile> enabledTiles;

    public UpdateEnabledTileOutputData(Set<BaseTile> enabledTiles) {
        this.enabledTiles = enabledTiles;
    }

    public Set<BaseTile> getEnabledTiles() {
        return enabledTiles;
    }
}
