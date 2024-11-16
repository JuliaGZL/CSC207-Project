package mahjong;

import java.util.ArrayList;
import java.util.List;

public class YakuCalculator {
  
  private PlayerStats playerStats;
  private int fan;
  private int fu;
  private List<Yaku> yakuList;
  private boolean hasYakuman;

  public YakuCalculator(PlayerStats playerStats) {
    this.playerStats = playerStats;
    this.fan = 0;
    this.fu = 0;
    this.yakuList = new ArrayList<>();
    this.hasYakuman = false;
  }

  private void getTenhouChiihouYaku() {
    if (playerStats.isTenhou()) {
      yakuList.add(Yaku.Tenhou);
    } else if (playerStats.isChiihou()) {
      yakuList.add(Yaku.Chiihou);
    } 
  }

  private void get_国士無双()
	{
		if (playerStats.isMenzenchin())
		{
			if (Rule.isKokushiShape(playerStats.getHand()))
			{
				// 判定13面
				{
					auto copybasetiles = basetiles;
					copybasetiles.pop_back();
					const static std::vector<BaseTile> raw{_1m, _9m, _1s, _9s, _1p, _9p, _1z, _2z, _3z, _4z, _5z, _6z, _7z};

					sort(copybasetiles.begin(), copybasetiles.end());
					if (is_same_container(raw, basetiles))
					{
						最大手役_番符.first.push_back(Yaku::Koukushimusou_13);
						国士無双13 = true;
					}
					else
					{
						最大手役_番符.first.push_back(Yaku::Kokushimusou);
						国士無双 = true;
					}
				}
				役満 = true;
			}
		}
		return false;
	}

  private void getIppatsuYaku() {
    if (playerStats.isIppatsu()) {
      yakuList.add(Yaku.Ippatsu);
    } 
  }

  private void getRinshanKaihouYaku() {
    if (playerStats.isRinshan()) {
      yakuList.add(Yaku.Rinshankaihou);
    } 
  }

  private void getHaiteiHouteiYaku() {
    if (playerStats.isHaitei()) {
      yakuList.add(Yaku.Haiteiraoyue);
    } else if (playerStats.isHoutei()) {
      yakuList.add(Yaku.Houteiraoyu);
    } 
  }

  private void getChankanYaku() {
    if (playerStats.isChankan()) {
      yakuList.add(Yaku.Chankan);
    } 
  }

  private void getRiichiYaku() {
    if (playerStats.isDoubleRiichi()) {
      yakuList.add(Yaku.Dabururiichi);
    } else if (playerStats.isRiichi()) {
      yakuList.add(Yaku.Riichi);
    } 
  }

  private void getTsumoYaku() {
    if (playerStats.isTsumo() && playerStats.isMenzenchin()) {
      yakuList.add(Yaku.Menzentsumo);
    } 
  }

  private void getKouiYaku(){
    getRiichiYaku();
    getTsumoYaku();
  }

  private void getGuuzenYaku() {
    getChankanYaku();
    getRinshanKaihouYaku();
    getHaiteiHouteiYaku();
    getIppatsuYaku();
  }

  private void getYakuman(){
    getTenhouChiihouYaku();
  }

  
}
