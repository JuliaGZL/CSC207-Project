package mahjong;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class YakuCalculator {

  private PlayerStats playerStats;
  private int fan;
  private int fu;
  private List<Yaku> maxYakuList;
  private boolean hasYakuman;
  private BaseTile winningTile;
  private List<BaseTile> copyHand;
  private List<BaseTile> originalHand;
  private int ChinitsuColor;
  private boolean hasSpecialYaku;

  public YakuCalculator(PlayerStats playerStats) {
    this.playerStats = playerStats;
    this.fan = 0;
    this.fu = 0;
    this.hasYakuman = false;
    this.hasSpecialYaku = true;
    this.maxYakuList = new ArrayList<>();
    this.originalHand = playerStats.getBaseHands();
    this.ChinitsuColor = Rule.getChinitsuColor(originalHand);
    this.winningTile = originalHand.get(originalHand.size() - 1);
    this.copyHand = new ArrayList<>(originalHand);
    this.copyHand.remove(copyHand.size() - 1);
    copyHand.sort(null);
    copyHand.add(winningTile);
    originalHand.sort(null);
  }

  private void getTenhouChiihouYaku(List<Yaku> yakuList) {
    if (playerStats.isTenhou()) {
      yakuList.add(Yaku.Tenhou);
    } else if (playerStats.isChiihou()) {
      yakuList.add(Yaku.Chiihou);
    }
  }

  private boolean getKoukushimusou(List<Yaku> yakuList) {
    if (playerStats.isMenzenchin()) {
      if (Rule.isKokushiShape(copyHand)) {
        yakuList.add(Yaku.Koukushimusou13);
        hasYakuman = true;
        return true;

      } else if (Rule.isKokushiShape(originalHand)) {
        yakuList.add(Yaku.Koukushimusou);
        hasYakuman = true;
        return true;
      }
    }
    return false;
  }

  private boolean getChurenPoutouYaku(List<Yaku> yakuList) {
    if (playerStats.isMenzenchin()) {
      if (Rule.isChurenShape(copyHand, ChinitsuColor)) {
        yakuList.add(Yaku.Chuurenpoutou9);
        hasYakuman = true;
        return true;
      } else if (Rule.isChurenShape(originalHand, ChinitsuColor)) {
        yakuList.add(Yaku.Chuurenpoutou);
        hasYakuman = true;
        return true;
      }
    }
    return false;
  }

  private void getTsuuiisouYaku(List<Yaku> yakuList) {
    if (ChinitsuColor == 3) {
      yakuList.add(Yaku.Tsuuiisou);
      hasYakuman = true;
    }
  }

  private void getIppatsuYaku(List<Yaku> yakuList) {
    if (playerStats.isIppatsu()) {
      yakuList.add(Yaku.Ippatsu);
    }
  }

  private void getRinshanKaihouYaku(List<Yaku> yakuList) {
    if (playerStats.isRinshan()) {
      yakuList.add(Yaku.Rinshankaihou);
    }
  }

  private void getHaiteiHouteiYaku(List<Yaku> yakuList) {
    if (playerStats.isHaitei()) {
      yakuList.add(Yaku.Haiteiraoyue);
    } else if (playerStats.isHoutei()) {
      yakuList.add(Yaku.Houteiraoyu);
    }
  }

  private void getChankanYaku(List<Yaku> yakuList) {
    if (playerStats.isChankan()) {
      yakuList.add(Yaku.Chankan);
    }
  }

  private void getRiichiYaku(List<Yaku> yakuList) {
    if (playerStats.isDoubleRiichi()) {
      yakuList.add(Yaku.Dabururiichi);
    } else if (playerStats.isRiichi()) {
      yakuList.add(Yaku.Riichi);
    }
  }

  private void getTsumoYaku(List<Yaku> yakuList) {
    if (playerStats.isTsumo() && playerStats.isMenzenchin()) {
      yakuList.add(Yaku.Menzentsumo);
    }
  }

  private void getKouiYaku(List<Yaku> yakuList) {
    getRiichiYaku(yakuList);
    getTsumoYaku(yakuList);
  }

  private void getGuuzenYaku(List<Yaku> yakuList) {
    getChankanYaku(yakuList);
    getRinshanKaihouYaku(yakuList);
    getHaiteiHouteiYaku(yakuList);
    getIppatsuYaku(yakuList);
  }

  private void getSepcialYakuman(List<Yaku> yakuList) {
    getTenhouChiihouYaku(yakuList);
    if (!getKoukushimusou(yakuList)) {
      getTsuuiisouYaku(yakuList);
      if (!getChurenPoutouYaku(yakuList)) {
        hasSpecialYaku = false;
      }
    }
    hasSpecialYaku = true;
  }

  public List<Yaku> getYakuman(List<String> tileGroupString, BaseTile selfWind, BaseTile prevalentWind, boolean[] yakuman) {
    List<Yaku> yakus = new ArrayList<>();

    // 统计所有的字牌刻子和对子
    boolean[] jihaiKoutsu = new boolean[7];
    boolean[] jihaiToitsu = new boolean[7];
    tileGroupString.forEach(s -> {
        if (s.charAt(1) == TileGroup.markJihai) {
            if (s.charAt(2) == TileGroup.markKoutsu || s.charAt(2) == TileGroup.markKantsu) {
                jihaiKoutsu[s.charAt(0) - '1'] = true;
            }
            if (s.charAt(2) == TileGroup.markToitsu) {
                jihaiToitsu[s.charAt(0) - '1'] = true;
            }
        }
    });

    // 判断单骑
    boolean danQi = tileGroupString.stream().anyMatch(s -> s.charAt(2) == TileGroup.markToitsu && s.length() == 4);

    // 统计暗刻数
    long numAnKe = tileGroupString.stream().filter(s -> (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu) ||
            (s.length() == 4 && s.charAt(2) == TileGroup.markKoutsu && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markTsumo3rd)) ||
            (s.charAt(2) == TileGroup.markKantsu && s.charAt(3) == TileGroup.markAnkan)).count();

    // 统计杠子数
    long numGangZi = tileGroupString.stream().filter(s -> s.charAt(2) == TileGroup.markKantsu).count();
    
    // 判断清老头
    boolean qingLaoTou = tileGroupString.stream().allMatch(Rule::isRoutouhai);
    
    // 判断绿一色
    boolean lvYiSe = tileGroupString.stream().allMatch(Rule::isGreenpai);

    if (jihaiKoutsu[4] && jihaiKoutsu[5] && jihaiKoutsu[6]) {
        yakus.add(Yaku.Daisangen);
        yakuman[0] = true;
    }

    if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
        yakus.add(Yaku.Daisuushii);
        yakuman[0] = true;
    } else {
        if (jihaiToitsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
            yakus.add(Yaku.Shousuushii);
            yakuman[0] = true;
        }
        if (jihaiKoutsu[0] && jihaiToitsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
            yakus.add(Yaku.Shousuushii);
            yakuman[0] = true;
        }
        if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiToitsu[2] && jihaiKoutsu[3]) {
            yakus.add(Yaku.Shousuushii);
            yakuman[0] = true;
        }
        if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiToitsu[3]) {
            yakus.add(Yaku.Shousuushii);
            yakuman[0] = true;
        }
    }

    if (numAnKe == 4) {
        if (danQi) {
            yakus.add(Yaku.Suuankou_1);
            yakuman[0] = true;
        } else {
            yakus.add(Yaku.Suuankou);
            yakuman[0] = true;
        }
    }

    if (numGangZi == 4) {
        yakus.add(Yaku.Suukantsu);
        yakuman[0] = true;
    }

    if (qingLaoTou) {
        yakus.add(Yaku.Chinroutou);
        yakuman[0] = true;
    }

    if (lvYiSe) {
        yakus.add(Yaku.Ryuiisou);
        yakuman[0] = true;
    }

    return yakus;
}

  public Pair<List<Yaku>, Integer> getYaku(List<String> tileGroupString, Wind selfWind, Wind prevalentWind, boolean menQing) {
    List<Yaku> yakus = new ArrayList<>(16);
    int fu = 20;

    // 判断单骑
    boolean danQi = tileGroupString.stream().anyMatch(s -> s.charAt(2) == TileGroup.markToitsu && s.length() == 4);

    // DEBUG : 判断门清。确保此处无问题可以注释掉这部分代码
    boolean _menQing = tileGroupString.stream().allMatch(s -> s.length() == 3 || (s.length() == 4 && s.charAt(3) != TileGroup.markFuuro));
    if (_menQing != menQing) {
        throw new RuntimeException(String.format("Debug: Player门清状态与牌型判定函数不符合!\ntile_group_string = %s", String.join(" ", tileGroupString)));
    }

    // 判断7对
    if (tileGroupString.size() == 7) {
        yakus.add(Yaku.Chiitoitsu);
    }

    // 判断有没有顺子
    boolean hasShunzi = tileGroupString.stream().anyMatch(s -> s.charAt(2) == TileGroup.markShuntsu);
    if (!hasShunzi && tileGroupString.size() != 7) {
        yakus.add(Yaku.Toitoiho);
    }

    // 判断是不是断幺九
    boolean duanYaoJiu = tileGroupString.stream().noneMatch(s -> isDaiYaoJiu(s) || isZiPai(s));

    // 统计m清一色
    boolean mQingYiSe = tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markManzu);
    boolean mHunYiSe = !mQingYiSe && tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markManzu || s.charAt(1) == TileGroup.markJihai);

    // 统计P清一色
    boolean pQingYiSe = tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markPinzu);
    boolean pHunYiSe = !pQingYiSe && tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markPinzu || s.charAt(1) == TileGroup.markJihai);

    // 统计S清一色
    boolean sQingYiSe = tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markShuntsu);
    boolean sHunYiSe = !sQingYiSe && tileGroupString.stream().allMatch(s -> s.charAt(1) == TileGroup.markShuntsu || s.charAt(1) == TileGroup.markJihai);

    // 统计所有的字牌刻子和对子
    boolean[] jihaiKoutsu = new boolean[7];
    boolean[] jihaiToitsu = new boolean[7];
    tileGroupString.forEach(s -> {
        if (s.charAt(1) == TileGroup.markJihai) {
            if (s.charAt(2) == TileGroup.markKoutsu || s.charAt(2) == TileGroup.markKantsu) {
                jihaiKoutsu[s.charAt(0) - '1'] = true;
            }
            if (s.charAt(2) == TileGroup.markToitsu) {
                jihaiToitsu[s.charAt(0) - '1'] = true;
            }
        }
    });

    // 统计暗刻数
    long numAnKe = tileGroupString.stream().filter(s -> (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu) ||
            (s.length() == 4 && s.charAt(2) == TileGroup.markKoutsu && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markTsumo3rd)) ||
            (s.charAt(2) == TileGroup.markKantsu && s.charAt(3) == TileGroup.markAnkan)).count();

    // 统计杠子数
    long numGangZi = tileGroupString.stream().filter(s -> s.charAt(2) == TileGroup.markKantsu).count();

    // 判断混老头
    boolean hunLaoTou = tileGroupString.stream().allMatch(s -> isLaoTouPai(s) || isZiPai(s));

    // 统计幺九状态
    boolean chunQuanDaiYao = tileGroupString.stream().allMatch(this::isDaiYaoJiu);
    boolean quanDaiYaoJiu = !hunLaoTou && !chunQuanDaiYao && tileGroupString.stream().allMatch(s -> isDaiYaoJiu(s) || isZiPai(s));

    // 判断平和
    boolean pingHe = menQing && !danQi && tileGroupString.stream().noneMatch(s -> isYakuPaiDuizi(s, selfWind, prevalentWind)) &&
            tileGroupString.stream().allMatch(s -> s.charAt(2) == TileGroup.markToitsu || s.charAt(2) == TileGroup.markShuntsu) &&
            tileGroupString.stream().allMatch(s -> s.length() == 3 || (s.charAt(3) != TileGroup.markTsumo2nd && s.charAt(3) != TileGroup.markRon2nd && (s.charAt(2) != TileGroup.markToitsu || s.charAt(3) != TileGroup.markRon1st) &&
                    ((s.charAt(3) != TileGroup.markTsumo3rd && s.charAt(3) != TileGroup.markRon3rd) || s.charAt(0) != '1') && ((s.charAt(3) != TileGroup.markTsumo1st && s.charAt(3) != TileGroup.markRon1st) || s.charAt(0) != '7'))) &&
            tileGroupString.size() == 5;

    List<String> tileGroupStringNo4 = remove4(tileGroupString);

    // 判断三色同顺
    boolean sanSeTongShun = false;
    String[] sanSeTongShunTiles = {
            "1mS", "2mS", "3mS", "4mS", "5mS", "6mS", "7mS",
            "1pS", "2pS", "3pS", "4pS", "5pS", "6pS", "7pS",
            "1sS", "2sS", "3sS", "4sS", "5sS", "6sS", "7sS"
    };
    for (int i = 0; i < 7; ++i) {
        if (tileGroupStringNo4.contains(sanSeTongShunTiles[i]) &&
                tileGroupStringNo4.contains(sanSeTongShunTiles[i + 7]) &&
                tileGroupStringNo4.contains(sanSeTongShunTiles[i + 14])) {
            sanSeTongShun = true;
            break;
        }
    }

    // 判断三色同刻
    boolean sanSeTongKe = false;
    String[] sanSeTongKeTiles = {
            "1mK", "2mK", "3mK", "4mK", "5mK", "6mK", "7mK", "8mK", "9mK",
            "1pK", "2pK", "3pK", "4pK", "5pK", "6pK", "7pK", "8pK", "9pK",
            "1sK", "2sK", "3sK", "4sK", "5sK", "6sK", "7sK", "8sK", "9sK"
    };
    for (int i = 0; i < 9; ++i) {
        if (tileGroupStringNo4.contains(sanSeTongKeTiles[i]) &&
                tileGroupStringNo4.contains(sanSeTongKeTiles[i + 9]) &&
                tileGroupStringNo4.contains(sanSeTongKeTiles[i + 18])) {
            sanSeTongKe = true;
            break;
        }
    }

    // 判断一气通贯
    boolean yiQiTongGuan = false;
    String[] yiQiTongGuanM = {"1mS", "4mS", "7mS"};
    String[] yiQiTongGuanP = {"1pS", "4pS", "7pS"};
    String[] yiQiTongGuanS = {"1sS", "4sS", "7sS"};

    yiQiTongGuan |= Arrays.stream(yiQiTongGuanM).allMatch(tileGroupStringNo4::contains);
    yiQiTongGuan |= Arrays.stream(yiQiTongGuanP).allMatch(tileGroupStringNo4::contains);
    yiQiTongGuan |= Arrays.stream(yiQiTongGuanS).allMatch(tileGroupStringNo4::contains);

    String[] shunziPaixing = {
            "1sS", "2sS", "3sS", "4sS", "5sS", "6sS", "7sS",
            "1pS", "2pS", "3pS", "4pS", "5pS", "6pS", "7pS",
            "1mS", "2mS", "3mS", "4mS", "5mS", "6mS", "7mS"
    };
    // 判断二杯口 && 一杯口
    int nBeikou = 0;
    if (menQing) {
        for (String tiles : shunziPaixing) {
            if (tileGroupStringNo4.stream().filter(s -> s.equals(tiles)).count() >= 2) {
                nBeikou++;
            }
        }
    }

    // 开始统计
    if (nBeikou == 2) {
        yakus.add(Yaku.Rianpeikou);
    } else if (nBeikou == 1) {
        yakus.add(Yaku.Ippeikou);
    }

    if (yiQiTongGuan) {
        if (menQing) {
            yakus.add(Yaku.Ikkitsuukan);
        } else {
            yakus.add(Yaku.Ikkitsuukan_Naki);
        }
    }

    if (sanSeTongShun) {
        if (menQing) {
            yakus.add(Yaku.Sanshokudoujun);
        } else {
            yakus.add(Yaku.Sanshokudoujun_Naki);
        }
    }

    if (sanSeTongKe) {
        yakus.add(Yaku.Sanshokudoukou);
    }

    if (sQingYiSe || mQingYiSe || pQingYiSe) {
        if (menQing) {
            yakus.add(Yaku.Chinitsu);
        } else {
            yakus.add(Yaku.Chinitsu_Naki);
        }
    } else {
        if (sHunYiSe || mHunYiSe || pHunYiSe) {
            if (menQing) {
                yakus.add(Yaku.Honitsu);
            } else {
                yakus.add(Yaku.Honitsu_Naki);
            }
        }
    }

    if (numAnKe == 3) {
        yakus.add(Yaku.Sanankou);
    }
    if (numGangZi == 3) {
        yakus.add(Yaku.Sankantsu);
    }
    if (hunLaoTou) {
        yakus.add(Yaku.Honroutou);
    }

    if (duanYaoJiu) {
        yakus.add(Yaku.Tanyao);
    }

    if (chunQuanDaiYao) {
        if (menQing) {
            yakus.add(Yaku.Junchantaiyaochu);
        } else {
            yakus.add(Yaku.Junchantaiyaochu_Naki);
        }
    } else if (quanDaiYaoJiu && !hunLaoTou) {
        if (menQing) {
            yakus.add(Yaku.Honchantaiyaochu);
        } else {
            yakus.add(Yaku.Honchantaiyaochu_Naki);
        }
    }

    // 小三元
    if (jihaiKoutsu[4] && jihaiKoutsu[5] && jihaiToitsu[6]) {
        yakus.add(Yaku.Shousangen);
    } else if (jihaiKoutsu[4] && jihaiToitsu[5] && jihaiKoutsu[6]) {
        yakus.add(Yaku.Shousangen);
    } else if (jihaiToitsu[4] && jihaiKoutsu[5] && jihaiKoutsu[6]) {
        yakus.add(Yaku.Shousangen);
    }

    // 判断役牌
    if (jihaiKoutsu[4]) {
        yakus.add(Yaku.Yakuhai_Haku);
    }
    if (jihaiKoutsu[5]) {
        yakus.add(Yaku.Yakuhai_Hatsu);
    }
    if (jihaiKoutsu[6]) {
        yakus.add(Yaku.Yakuhai_Chu);
    }

    if (prevalentWind == Wind.East && jihaiKoutsu[0]) {
        yakus.add(Yaku.Bakaze_Ton);
    }
    if (prevalentWind == Wind.South && jihaiKoutsu[1]) {
        yakus.add(Yaku.Bakaze_Nan);
    }
    if (prevalentWind == Wind.West && jihaiKoutsu[2]) {
        yakus.add(Yaku.Bakaze_Sha);
    }
    if (prevalentWind == Wind.North && jihaiKoutsu[3]) {
        yakus.add(Yaku.Bakaze_Pei);
    }

    if (selfWind == Wind.East && jihaiKoutsu[0]) {
        yakus.add(Yaku.Jikaze_Ton);
    }
    if (selfWind == Wind.South && jihaiKoutsu[1]) {
        yakus.add(Yaku.Jikaze_Nan);
    }
    if (selfWind == Wind.West && jihaiKoutsu[2]) {
        yakus.add(Yaku.Jikaze_Sha);
    }
    if (selfWind == Wind.North && jihaiKoutsu[3]) {
        yakus.add(Yaku.Jikaze_Pei);
    }

    if (pingHe) {
        yakus.add(Yaku.Pinfu);
    }

    // 计算符数

    // 听牌型
    if (danQi) {
        fu += 2;
    }
    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && s.charAt(2) == TileGroup.markShuntsu && (s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markRon2nd))) {
        fu += 2;
    }

    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && s.charAt(2) == TileGroup.markShuntsu && ((s.charAt(0) == '1' && (s.charAt(3) == TileGroup.markTsumo3rd || s.charAt(3) == TileGroup.markRon3rd)) || (s.charAt(0) == '7' && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markRon1st))))) {
        fu += 2;
    }

    // 和了方式
    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markTsumo3rd)) && !pingHe) {
        fu += 2;
    }

    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && (s.charAt(3) == TileGroup.markRon1st || s.charAt(3) == TileGroup.markRon2nd || s.charAt(3) == TileGroup.markRon3rd)) && menQing) {
        fu += 10;
    }

    // 雀头符
    tileGroupString.forEach(s -> {
        fu += isYakuPaiDuizi(s, selfWind, prevalentWind) ? 2 : 0;
    });

    // 面子符
    tileGroupString.forEach(s -> {
        if (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu) {
            fu += isDaiYaoJiuZi(s) ? 8 : 4;
        }
        if (s.length() == 4) {
            switch (s.charAt(2)) {
                case TileGroup.markShuntsu:
                    break;
                case TileGroup.markKoutsu:
                    switch (s.charAt(3)) {
                        case TileGroup.markTsumo1st:
                        case TileGroup.markTsumo2nd:
                        case TileGroup.markTsumo3rd:
                            fu += isDaiYaoJiuZi(s) ? 8 : 4;
                            break;
                        case TileGroup.markRon1st:
                        case TileGroup.markRon2nd:
                        case TileGroup.markRon3rd:
                        case TileGroup.markF:
                            fu += isDaiYaoJiuZi(s) ? 4 : 2;
                            break;
                    }
                    break;
                case TileGroup.markKantsu:
                    if (s.charAt(3) == TileGroup.markMinkan) {
                        fu += isDaiYaoJiuZi(s) ? 16 : 8;
                    } else if (s.charAt(3) == TileGroup.markAnkan) {
                        fu += isDaiYaoJiuZi(s) ? 32 : 16;
                    }
                    break;
            }
        }
    });

    // 副露平和型，前面应该一定是20符
    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && (s.charAt(3) == TileGroup.markRon1st || s.charAt(3) == TileGroup.markRon2nd || s.charAt(3) == TileGroup.markRon3rd)) && !menQing) {
        if (fu == 20) {
            fu = 30;
        }
    }

    // 自摸平和 20符
    if (tileGroupString.stream().anyMatch(s -> s.length() == 4 && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markTsumo3rd)) && pingHe) {
        fu = 20;
    }

    if (fu % 10 != 0) {
        fu = (fu / 10) * 10 + 10;
    }

    // 注意七对子统一25符，并且不跳符。
    if (tileGroupString.size() == 7) {
        fu = 25;
    }

    return new Pair<>(yakus, fu);
}

