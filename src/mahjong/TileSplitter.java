package mahjong;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TileSplitter {

  private static TileSplitter instance = null;

  private CompletedTiles completedTiles;
  private boolean hasHead;

  // Singleton pattern
  private TileSplitter() {
    reset();
  }

  public static TileSplitter getInstance() {
    if (instance == null) {
      instance = new TileSplitter();
    }
    return instance;
  }

  public void reset() {
    completedTiles = new CompletedTiles();
    hasHead = false;
  }
  
vector<CompletedTiles> TileSplitter::get_all_completed_tiles(const vector<BaseTile> &tiles)
	{
		if (tiles.size() == 0)
		{
			return {completed_tiles};
		}
		vector<CompletedTiles> ret, all_completed_tiles;
		vector<BaseTile> tmp_tiles;
		int index = 0;
		bool flag = false; // 只有能成牌的才是true
		// 对、刻、顺都不能成，提前进入死路，flag=false，直接return {}

		for (int index = 0; index < tiles.size(); ++index)
		{
			if (index > 0 && tiles[index] == tiles[index - 1])
			{ // Skip same tiles
				index++;
				continue;
			}

			BaseTile this_tile = tiles[index];
			// 1. Find Toitsu (Head)
			if (!has_head)
			{
				if (count(tiles.begin(), tiles.end(), this_tile) >= 2)
				{
					flag = true;
					tmp_tiles = tiles; // 复制一份当前手牌
					TileGroup tmp_group;

					// 设置全局状态的head已经并移除对子
					tmp_group.type = TileGroup::Type::Toitsu;
					tmp_group.set_tiles({this_tile, this_tile});
					erase_n(tmp_tiles, this_tile, 2);
					has_head = true;
					completed_tiles.head = tmp_group;

					// 根据当前状态递归
					all_completed_tiles = get_all_completed_tiles(tmp_tiles);
					ret.insert(ret.end(), all_completed_tiles.begin(), all_completed_tiles.end());

					// 恢复系统状态
					has_head = false;
				}
			}

			// 2. Find Koutsu
			if (count(tiles.begin(), tiles.end(), this_tile) >= 3)
			{
				flag = true;
				tmp_tiles = tiles; // 复制一份当前手牌
				TileGroup tmp_group;

				// 设置全局状态的head已经并移除刻子
				tmp_group.type = TileGroup::Type::Koutsu;
				tmp_group.set_tiles({this_tile, this_tile, this_tile});
				erase_n(tmp_tiles, this_tile, 3);
				// 设置临时状态
				completed_tiles.body.push_back(tmp_group);
				// 根据当前状态递归
				all_completed_tiles = get_all_completed_tiles(tmp_tiles);
				ret.insert(ret.end(), all_completed_tiles.begin(), all_completed_tiles.end());
				// 恢复状态
				completed_tiles.body.pop_back();
			}

			// 3. Find Shuntsu
			if (!is_in(shuntsu_bad_head, this_tile) && is_in(tiles, BaseTile(tiles[index] + 1)) && is_in(tiles, BaseTile(tiles[index] + 2)))
			{
				flag = true;
				tmp_tiles = tiles; // 复制一份当前手牌
				TileGroup tmp_group;
				tmp_group.type = TileGroup::Type::Shuntsu;
				tmp_group.set_tiles({tiles[index], BaseTile(tiles[index] + 1), BaseTile(tiles[index] + 2)});
				erase_n(tmp_tiles, tiles[index], 1);
				erase_n(tmp_tiles, BaseTile(tiles[index] + 1), 1);
				erase_n(tmp_tiles, BaseTile(tiles[index] + 2), 1);

				// 设置临时状态
				completed_tiles.body.push_back(tmp_group);
				// 根据当前状态递归
				all_completed_tiles = get_all_completed_tiles(tmp_tiles);
				ret.insert(ret.end(), all_completed_tiles.begin(), all_completed_tiles.end());
				// 恢复状态
				completed_tiles.body.pop_back();
			}

			if (!flag)
			{
				return {};
			}
		}

		return ret;
	}

  public List<CompletedTiles> getAllCompletedTiles(List<BaseTile> tiles) {
    if (tiles.size() == 0) {
      return Collections.singletonList(completedTiles);
    }
    List<CompletedTiles> ret = new ArrayList<>();
    List<CompletedTiles> allCompletedTiles;
    List<BaseTile> tmpTiles;
    boolean flag = false;

    for (int index = 0; index < tiles.size(); ++index) {
      if (index > 0 && tiles.get(index).equals(tiles.get(index - 1))) {
        continue;
      }

      BaseTile thisTile = tiles.get(index);
      // 1. Find Toitsu (Head)
      if (!hasHead) {
        if (Collections.frequency(tiles, thisTile) >= 2) {
          flag = true;
          tmpTiles = new ArrayList<>(tiles);
          TileGroup tmpGroup = new TileGroup(TileGroup.Type.TOITSU);

          // Set global state head and remove pair
          tmpGroup.setTiles(List.of(thisTile, thisTile));
          eraseN(tmpTiles, thisTile, 2);
          hasHead = true;
          completedTiles.setHead(tmpGroup);

          // Recursively find all completed tiles
          allCompletedTiles = getAllCompletedTiles(tmpTiles);
          ret.addAll(allCompletedTiles);

          // Restore state
          hasHead = false;
        }
      }

      // 2. Find Koutsu
      if (Collections.frequency(tiles, thisTile) >= 3) {
        flag = true;
        tmpTiles = new ArrayList<>(tiles);
        TileGroup tmpGroup = new TileGroup(TileGroup.Type.KOUTSU);

        // Set global state and remove triplet
        tmpGroup.setTiles(List.of(thisTile, thisTile, thisTile));
        eraseN(tmpTiles, thisTile, 3);
        completedTiles.getBody().add(tmpGroup);

        // Recursively find all completed tiles
        allCompletedTiles = getAllCompletedTiles(tmpTiles);
        ret.addAll(allCompletedTiles);

        // Restore state
        completedTiles.getBody().remove(completedTiles.getBody().size() - 1);
      }

      // 3. Find Shuntsu
      if (!Rule.isShuntsuBadHead(thisTile) && tiles.contains(new BaseTile(thisTile.ordinal() + 1)) && tiles.contains(new BaseTile(thisTile.getValue() + 2))) {
        flag = true;
        tmpTiles = new ArrayList<>(tiles);
        TileGroup tmpGroup = new TileGroup(TileGroup.Type.SHUNTSU);
        tmpGroup.setTiles(List.of(thisTile, new BaseTile(thisTile.getValue() + 1), new BaseTile(thisTile.getValue() + 2)));
        eraseN(tmpTiles, thisTile, 1);
        eraseN(tmpTiles, new BaseTile(thisTile.getValue() + 1), 1);
        eraseN(tmpTiles, new BaseTile(thisTile.getValue() + 2), 1);
        completedTiles.getBody().add(tmpGroup);

        // Recursively find all completed tiles
        allCompletedTiles = getAllCompletedTiles(tmpTiles);
        ret.addAll(allCompletedTiles);

        // Restore state
        completedTiles.getBody().remove(completedTiles.getBody().size() - 1);
      }

      if (!flag) {
        return Collections.emptyList();
      }
    }

    return ret;
  }

  private void eraseN(List<BaseTile> tiles, BaseTile tile, int n) {
    for (int i = 0; i < n; i++) {
      tiles.remove(tile);
    }
  }


}
