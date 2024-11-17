package mahjong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class YakuCalculator {

  private PlayerStats playerStats;
  private int fan;
  private int fu;
  private List<Yaku> yakuList;
  private boolean hasYakuman;
  private boolean Koukushimusou;
  private BaseTile winningTile;
  private List<BaseTile> copyHand;
  private List<BaseTile> originalHand;

  public YakuCalculator(PlayerStats playerStats) {
    this.playerStats = playerStats;
    this.fan = 0;
    this.fu = 0;
    this.yakuList = new ArrayList<>();
    this.hasYakuman = false;
    this.originalHand = playerStats.getBaseHands();
    this.winningTile = originalHand.get(originalHand.size() - 1);
    this.copyHand = new ArrayList<>(originalHand);
    this.copyHand.remove(copyHand.size() - 1);
    copyHand.sort(null);
    copyHand.add(winningTile);
    originalHand.sort(null);
  }

  private void getTenhouChiihouYaku() {
    if (playerStats.isTenhou()) {
      yakuList.add(Yaku.Tenhou);
    } else if (playerStats.isChiihou()) {
      yakuList.add(Yaku.Chiihou);
    }
  }

  private void getKoukushimusou() {
    if (playerStats.isMenzenchin()) {
      if (Rule.isKokushiShape(copyHand)) {
        yakuList.add(Yaku.Koukushimusou13);
        Koukushimusou = true;

        hasYakuman = true;
      } else {
        yakuList.add(Yaku.Koukushimusou);
        Koukushimusou = true;
        hasYakuman = true;
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

  private void getKouiYaku() {
    getRiichiYaku();
    getTsumoYaku();
  }

  private void getGuuzenYaku() {
    getChankanYaku();
    getRinshanKaihouYaku();
    getHaiteiHouteiYaku();
    getIppatsuYaku();
  }

  private void getYakuman() {
    getTenhouChiihouYaku();
  }

}
