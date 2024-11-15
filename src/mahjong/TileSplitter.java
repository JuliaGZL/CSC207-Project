package mahjong;

import java.util.ArrayList;

public class TileSpilter {
  static final  char markToitsu = ':';
  static final char markShuntsu = 'S';
  static final char markKoutsu = 'K';
  static final char markKantsu = '|';
  static final char markAnkan = '+';
  static final char markMinkan = '-';
  static final char markFuuro = '-';
  static final char markTsumo1st = '!';
  static final char markTsumo2nd = '@';
  static final char markTsumo3rd = '#';
  static final char markRon1st = '$';
  static final char markRon2nd = '%';
  static final char markRon3rd = '^';
  

  public static char[] makeTileGroup(String t, char mark) {
    char[] ret = new char[] { t.charAt(0), t.charAt(1), mark };
    return ret;
  }

  public static char[] makeTileGroup(String t, char mark1, char mark2) {
    char[] ret = new char[] { t.charAt(0), t.charAt(1), mark1, mark2 };
    return ret;
  }

  public static char[] makeToitsu(String t) {
    return makeTileGroup(t, markToitsu);
  }

  public static char[] makeShuntsu(String t) {
    return makeTileGroup(t, markShuntsu);
  }

  public static char[] makeShuntsufuuro(String t) {
    return makeTileGroup(t, markShuntsu, markFuuro);
  }

  public static char[] makeKoutsu(String t) {
    return makeTileGroup(t, markKoutsu);
  }

  public static char[] makeKoutsuFuuro(String t) {
    return makeTileGroup(t, markKoutsu, markFuuro);
  }

  public static char[] makeAnkan(String t) {
    return makeTileGroup(t, markKantsu, markAnkan);
  }

  public static char[] makeMinkan(String t) {
    return makeTileGroup(t, markKantsu, markMinkan);
  }

  public static ArrayList<ArrayList<String>> generate_tile_group_strings(const CompletedTiles &ct, const vector<CallGroup> &callgroups, bool tsumo, BaseTile last_tile)
	{
		profiler _("ScoreCounter.cpp/generate_tgs");
		vector<vector<string>> tile_group_strings;
		vector<string> raw_tile_group_string; // without consider where is the agari tile.

		if (ct.head.tiles.size() != 0)
			raw_tile_group_string.push_back(make_toitsu(ct.head.tiles[0])); // 例如1z2
		else if (ct.body.size() != 7)
			throw runtime_error("Unknown CompletedTiles object. No Head and not 7-toitsu");
		else // 七对的情况（即没有雀头，BODY_SIZE正好为7）
		{
			for (int i = 0; i < 7; ++i)
			{
				raw_tile_group_string.push_back(make_toitsu(ct.body[i].tiles[0]));
			}
		}

		for (auto callgroup : callgroups)
		{
			switch (callgroup.type)
			{
			case CallGroup::Chi:
			{
				raw_tile_group_string.push_back(make_shuntsu_fuuro(callgroup.tiles[0]->tile)); // 例如1sS- 即1s2s3s (8sS无效)
				continue;
			}
			case CallGroup::Pon:
			{
				raw_tile_group_string.push_back(make_koutsu_fuuro(callgroup.tiles[0]->tile)); // 例如1sK- 即1s1s1s
				continue;
			}
			case CallGroup::DaiMinKan:
			case CallGroup::KaKan:
			{
				raw_tile_group_string.push_back(make_minkan(callgroup.tiles[0]->tile)); // 例如3z4- 即西大明杠/加杠
				continue;
			}
			case CallGroup::AnKan:
			{
				raw_tile_group_string.push_back(make_ankan(callgroup.tiles[0]->tile)); // 例如4s4+ 即4s暗杠
				continue;
			}
			}
		}
		for (auto tilegroup : ct.body)
		{
			switch (tilegroup.type)
			{
			case TileGroup::Shuntsu:
			{
				raw_tile_group_string.push_back(make_shuntsu(tilegroup.tiles[0])); // 例如1sS
				continue;
			}
			case TileGroup::Koutsu:
			{
				raw_tile_group_string.push_back(make_koutsu(tilegroup.tiles[0])); // 例如1sK
				continue;
			}
			default:
				continue;
			}
		}

		// last_tile是否存在于tile_group中
		string last_tile_string = basetile_to_string(last_tile);
		for (auto &tile_group : raw_tile_group_string)
		{
			auto &attr = tile_group.back(); // 最后一个字符表示它的性质

			// 只考虑在手牌中的情况，所以说最后一个必须是:SK之一，其他情况略过
			if (attr == mark_koutsu || attr == mark_toitsu)
			{
				if (tile_group[0] == last_tile_string[0] && tile_group[1] == last_tile_string[1])
				{
					if (tsumo)
						tile_group += mark_tsumo_1st;
					else
						tile_group += mark_ron_1st;
					tile_group_strings.push_back(raw_tile_group_string);
					tile_group.pop_back();
				}
			}
			else if (attr == mark_shuntsu)
			{
				// 例如7sS
				// 胡7s进入第一个情况，胡8s进入第二个情况，胡9s进入第三个情况
				if (tile_group[0] == last_tile_string[0] && tile_group[1] == last_tile_string[1])
				{
					if (tsumo)
						tile_group += mark_tsumo_1st;
					else
						tile_group += mark_ron_1st;
					tile_group_strings.push_back(raw_tile_group_string);
					tile_group.pop_back();
				}
				else if ((tile_group[0] + 1) == last_tile_string[0] && tile_group[1] == last_tile_string[1])
				{
					if (tsumo)
						tile_group += mark_tsumo_2nd;
					else
						tile_group += mark_ron_2nd;
					tile_group_strings.push_back(raw_tile_group_string);
					tile_group.pop_back();
				}
				else if ((tile_group[0] + 2) == last_tile_string[0] && tile_group[1] == last_tile_string[1])
				{
					if (tsumo)
						tile_group += mark_tsumo_3rd;
					else
						tile_group += mark_ron_3rd;
					tile_group_strings.push_back(raw_tile_group_string);
					tile_group.pop_back();
				}
			}
		}

		// 消去tile_group_strings中完全相同的元素

		sort(tile_group_strings.begin(), tile_group_strings.end());
		auto iter = unique(tile_group_strings.begin(), tile_group_strings.end());
		tile_group_strings.erase(iter, tile_group_strings.end());

		return tile_group_strings;
	}

	/* 移除副露、胡牌等信息，创建复制 */
	public static ArrayList<String> remove_4(ArrayList<String> strs)
	{
		ArrayList<String> retstr(strs);

		for (String ret : retstr)
		{
			if (ret.size() == 4)
				ret.pop_back();
			if (ret[2] == '|')
				ret[2] = 'K';
		}
		return retstr;
	}

}
