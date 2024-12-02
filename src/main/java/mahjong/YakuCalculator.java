package mahjong;

import entity.CallGroup;
import entity.PlayerStats;
import entity.Tile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utils.Algorithm;
import utils.Pair;

/**
 * The YakuCalculator class is responsible for calculating the Yaku (winning
 * hand patterns) in a Mahjong game.
 */
public class YakuCalculator {

  private PlayerStats playerStats;
  private boolean hasYakuman;
  private BaseTile winningTile;
  private List<BaseTile> copyHand;
  private List<BaseTile> originalHand;
  private int chinitsuColor;
  private boolean hasSpecialYaku;
  private BaseTile selfWind;
  private BaseTile prevalentWind;
  private boolean isMenzenchin;

  /**
   * Constructs a YakuCalculator with the given player statistics.
   *
   * @param playerStats the statistics of the player
   */
  public YakuCalculator(PlayerStats playerStats) {
    this.playerStats = playerStats;
    this.hasYakuman = false;
    this.hasSpecialYaku = true;
    this.isMenzenchin = playerStats.isMenzenchin();
    this.selfWind = playerStats.getSelfWind();
    this.prevalentWind = playerStats.getPrevalentWind();
    this.originalHand = playerStats.getBaseHand();
    this.chinitsuColor = Rule.getChinitsuColor(originalHand);
    this.winningTile = originalHand.get(originalHand.size() - 1);
    this.copyHand = new ArrayList<>(originalHand);
    this.copyHand.remove(copyHand.size() - 1);
    copyHand.sort(null);
    copyHand.add(winningTile);
    originalHand.sort(null);
  }

  /**
   * Adds Tenhou or Chiihou Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getTenhouChiihouYaku(List<Yaku> yakuList) {
    if (playerStats.isTenhou()) {
      yakuList.add(Yaku.Tenhou);
    } else if (playerStats.isChiihou()) {
      yakuList.add(Yaku.Chiihou);
    }
  }

  /**
   * Checks for Koukushimusou Yaku and adds it to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   * @return true if Koukushimusou Yaku is found, false otherwise
   */
  private boolean getKoukushimusou(List<Yaku> yakuList) {
    if (isMenzenchin) {
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

  /**
   * Checks for Churen Poutou Yaku and adds it to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   * @return true if Churen Poutou Yaku is found, false otherwise
   */
  private boolean getChurenPoutouYaku(List<Yaku> yakuList) {
    if (isMenzenchin) {
      if (Rule.isChurenShape(copyHand, chinitsuColor)) {
        yakuList.add(Yaku.Chuurenpoutou9);
        hasYakuman = true;
        return true;
      } else if (Rule.isChurenShape(originalHand, chinitsuColor)) {
        yakuList.add(Yaku.Chuurenpoutou);
        hasYakuman = true;
        return true;
      }
    }
    return false;
  }

  /**
   * Adds Tsuuiisou Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getTsuuiisouYaku(List<Yaku> yakuList) {
    if (chinitsuColor == 3) {
      yakuList.add(Yaku.Tsuuiisou);
      hasYakuman = true;
    }
  }

  /**
   * Adds Ippatsu Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getIppatsuYaku(List<Yaku> yakuList) {
    if (playerStats.isIppatsu()) {
      yakuList.add(Yaku.Ippatsu);
    }
  }

  /**
   * Adds Rinshan Kaihou Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getRinshanKaihouYaku(List<Yaku> yakuList) {
    if (playerStats.isRinshan()) {
      yakuList.add(Yaku.Rinshankaihou);
    }
  }

  /**
   * Adds Haitei or Houtei Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getHaiteiHouteiYaku(List<Yaku> yakuList) {
    if (playerStats.isHaitei()) {
      yakuList.add(Yaku.Haiteiraoyue);
    } else if (playerStats.isHoutei()) {
      yakuList.add(Yaku.Houteiraoyu);
    }
  }

  /**
   * Adds Chankan Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getChankanYaku(List<Yaku> yakuList) {
    if (playerStats.isChankan()) {
      yakuList.add(Yaku.Chankan);
    }
  }

  /**
   * Adds Riichi or Double Riichi Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getRiichiYaku(List<Yaku> yakuList) {
    if (playerStats.isDoubleRiichi()) {
      yakuList.add(Yaku.Dabururiichi);
    } else if (playerStats.isRiichi()) {
      yakuList.add(Yaku.Riichi);
    }
  }

  /**
   * Adds Tsumo Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getTsumoYaku(List<Yaku> yakuList) {
    if (playerStats.isTsumo() && isMenzenchin) {
      yakuList.add(Yaku.Menzentsumo);
    }
  }

  /**
   * Adds Koui Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getKouiYaku(List<Yaku> yakuList) {
    getRiichiYaku(yakuList);
    getTsumoYaku(yakuList);
  }

  /**
   * Adds Guuzen Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getGuuzenYaku(List<Yaku> yakuList) {
    getChankanYaku(yakuList);
    getRinshanKaihouYaku(yakuList);
    getHaiteiHouteiYaku(yakuList);
    getIppatsuYaku(yakuList);
  }

  /**
   * Adds special Yakuman Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getSepcialYakuman(List<Yaku> yakuList) {
    getTenhouChiihouYaku(yakuList);
    if (!getKoukushimusou(yakuList)) {
      getTsuuiisouYaku(yakuList);
      if (!getChurenPoutouYaku(yakuList)) {
        hasSpecialYaku = false;
        return;
      }
    }
    hasSpecialYaku = true;
  }

  /**
   * Adds Daisangen Yaku to the given list if applicable.
   *
   * @param yakuList    the list of Yaku to add to
   * @param jihaiKoutsu the array indicating which jihai Koutsu are present
   */
  private void getDaisangenYaku(List<Yaku> yakuList, boolean[] jihaiKoutsu) {
    if (jihaiKoutsu[4] && jihaiKoutsu[5] && jihaiKoutsu[6]) {
      yakuList.add(Yaku.Daisangen);
      hasYakuman = true;
    }
  }

  /**
   * Adds Suushii Yaku to the given list if applicable.
   *
   * @param yakuList    the list of Yaku to add to
   * @param jihaiKoutsu the array indicating which jihai Koutsu are present
   * @param jihaiToitsu the array indicating which jihai Toitsu are present
   */
  private void getSuushiiYaku(List<Yaku> yakuList, boolean[] jihaiKoutsu, boolean[] jihaiToitsu) {
    if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
      yakuList.add(Yaku.Daisuushii);
      hasYakuman = true;
    } else {
      if (jihaiToitsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
        yakuList.add(Yaku.Shousuushii);
        hasYakuman = true;
      }
      if (jihaiKoutsu[0] && jihaiToitsu[1] && jihaiKoutsu[2] && jihaiKoutsu[3]) {
        yakuList.add(Yaku.Shousuushii);
        hasYakuman = true;
      }
      if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiToitsu[2] && jihaiKoutsu[3]) {
        yakuList.add(Yaku.Shousuushii);
        hasYakuman = true;
      }
      if (jihaiKoutsu[0] && jihaiKoutsu[1] && jihaiKoutsu[2] && jihaiToitsu[3]) {
        yakuList.add(Yaku.Shousuushii);
        hasYakuman = true;
      }
    }
  }

  /**
   * Adds Suuankou Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   * @param numAnkou the number of Ankou
   * @param tanki    whether the hand is a single wait
   */
  private void getSuuankouYaku(List<Yaku> yakuList, long numAnkou, boolean tanki) {
    if (numAnkou == 4) {
      if (tanki) {
        yakuList.add(Yaku.Suuankou1);
        hasYakuman = true;
      } else {
        yakuList.add(Yaku.Suuankou);
        hasYakuman = true;
      }
    }
  }

  /**
   * Adds Suukantsu Yaku to the given list if applicable.
   *
   * @param yakuList  the list of Yaku to add to
   * @param numKantsu the number of Kantsu
   */
  private void getSuukantsuYaku(List<Yaku> yakuList, long numKantsu) {
    if (numKantsu == 4) {
      yakuList.add(Yaku.Suukantsu);
      hasYakuman = true;
    }
  }

  /**
   * Adds Chinroutou Yaku to the given list if applicable.
   *
   * @param yakuList     the list of Yaku to add to
   * @param isChinroutou whether the hand is Chinroutou
   */
  private void getChinroutouYaku(List<Yaku> yakuList, boolean isChinroutou) {
    if (isChinroutou) {
      yakuList.add(Yaku.Chinroutou);
      hasYakuman = true;
    }
  }

  /**
   * Adds Ryuiisou Yaku to the given list if applicable.
   *
   * @param yakuList   the list of Yaku to add to
   * @param isRyuiisou whether the hand is Ryuiisou
   */
  private void getRyuiisouYaku(List<Yaku> yakuList, boolean isRyuiisou) {
    if (isRyuiisou) {
      yakuList.add(Yaku.Ryuiisou);
      hasYakuman = true;
    }
  }

  /**
   * Gets the list of Yakuman Yaku for the given tile group strings.
   *
   * @param tileGroupString the list of tile group strings
   * @return the list of Yakuman Yaku
   */
  private List<Yaku> getYakuman(List<String> tileGroupString) {
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
    final boolean tanki = tileGroupString.stream().anyMatch(
        s -> s.charAt(2) == TileGroup.markToitsu && s.length() == 4);

    // 统计暗刻数
    final long numAnkou = tileGroupString.stream().filter(
        s -> (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu)
            || (s.length() == 4 && s.charAt(2) == TileGroup.markKoutsu
                && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd
                    || s.charAt(3) == TileGroup.markTsumo3rd))
            || (s.charAt(2) == TileGroup.markKantsu && s.charAt(3) == TileGroup.markAnkan))
        .count();

    // 统计杠子数
    long numKantsu = tileGroupString.stream().filter(
        s -> s.charAt(2) == TileGroup.markKantsu).count();
    getSuukantsuYaku(yakus, numKantsu);

    // 判断清老头
    boolean isChinroutou = tileGroupString.stream().allMatch(Rule::isRoutouhai);
    getChinroutouYaku(yakus, isChinroutou);
    // 判断绿一色
    boolean isRyuiisou = tileGroupString.stream().allMatch(Rule::isGreenpai);
    getRyuiisouYaku(yakus, isRyuiisou);

    getDaisangenYaku(yakus, jihaiKoutsu);
    getSuushiiYaku(yakus, jihaiKoutsu, jihaiToitsu);
    getSuuankouYaku(yakus, numAnkou, tanki);

    return yakus;
  }

  /**
   * Gets the Yaku and Fan/Fu for the given tile group strings.
   *
   * @param tileGroupString the list of tile group strings
   * @return a pair containing the list of Yaku and a pair of Fan and Fu
   */
  private Pair<List<Yaku>, Pair<Integer, Integer>> getYakuAndFanFu(List<String> tileGroupString) {
    List<Yaku> yakus = new ArrayList<>(16);
    int fu = 20;

    // Check for tanki (single wait)
    final boolean tanki = isTanki(tileGroupString);

    System.out.println(tileGroupString);
    // Check for Chiitoitsu (Seven pairs)
    if (isChiitoitsu(tileGroupString)) {
      yakus.add(Yaku.Chiitoitsu);
      fu = 25; // Chiitoitsu always has 25 fu
    }

    // Check for Toitoiho (All triplets)
    boolean hasShunzi = hasShunzi(tileGroupString);
    if (isToitoiho(tileGroupString, hasShunzi)) {
      yakus.add(Yaku.Toitoiho);
    }

    // Check for Tanyao (All simples)
    boolean duanYaoJiu = isDuanYaoJiu(tileGroupString);
    if (duanYaoJiu) {
      yakus.add(Yaku.Tanyao);
    }

    // Check for Chinitsu and Honitsu
    Pair<Boolean, Boolean> chinitsuHonitsu = getChinitsuHonitsu(tileGroupString, chinitsuColor);
    boolean isChinitsu = chinitsuHonitsu.getFst();
    boolean isHonitsu = chinitsuHonitsu.getSnd();
    if (isChinitsu) {
      if (isMenzenchin) {
        yakus.add(Yaku.Chinitsu);
      } else {
        yakus.add(Yaku.ChinitsuNaki);
      }
    } else if (isHonitsu) {
      if (isMenzenchin) {
        yakus.add(Yaku.Honitsu);
      } else {
        yakus.add(Yaku.HonitsuNaki);
      }
    }

    // Get jihai Koutsu and Toitsu
    Pair<boolean[], boolean[]> jihaiCounts = getJihaiKoutsuToitsu(tileGroupString);
    final boolean[] jihaiKoutsu = jihaiCounts.getFst();
    final boolean[] jihaiToitsu = jihaiCounts.getSnd();

    // Check for Honroutou (All terminals and honors)
    boolean isHonroutou = isHonroutou(tileGroupString);
    if (isHonroutou) {
      yakus.add(Yaku.Honroutou);
    }

    // Check for Junchantaiyao and Chantaiyao
    boolean isJunchantaiyao = isJunchantaiyao(tileGroupString);
    boolean isChantaiyao = isChantaiyao(tileGroupString, isHonroutou, isJunchantaiyao);
    if (isJunchantaiyao) {
      if (isMenzenchin) {
        yakus.add(Yaku.Junchantaiyaochu);
      } else {
        yakus.add(Yaku.JunchantaiyaochuNaki);
      }
    } else if (isChantaiyao) {
      if (isMenzenchin) {
        yakus.add(Yaku.Honchantaiyaochu);
      } else {
        yakus.add(Yaku.HonchantaiyaochuNaki);
      }
    }

    // Check for Pinfu
    boolean isPinfu = isPinfu(tileGroupString, tanki);
    if (isPinfu) {
      yakus.add(Yaku.Pinfu);
    }

    // Remove the 4th character from tileGroupString elements
    List<String> tileGroupStringNo4 = TileGroup.remove4(tileGroupString);

    // Check for Sanshoku Doujun
    boolean sanShokuDoujun = isSanshokuDoujun(tileGroupStringNo4);
    if (sanShokuDoujun) {
      if (isMenzenchin) {
        yakus.add(Yaku.Sanshokudoujun);
      } else {
        yakus.add(Yaku.SanshokudoujunNaki);
      }
    }

    // Check for Sanshoku Doukou
    boolean sanShokuDoukou = isSanshokuDoukou(tileGroupStringNo4);
    if (sanShokuDoukou) {
      yakus.add(Yaku.Sanshokudoukou);
    }

    // Check for Ittsu (Straight)
    boolean isIttsu = isIttsu(tileGroupStringNo4);
    if (isIttsu) {
      if (isMenzenchin) {
        yakus.add(Yaku.Ikkitsuukan);
      } else {
        yakus.add(Yaku.IkkitsuukanNaki);
      }
    }

    // Count Ankou and Kantsu
    long numAnkou = countAnkou(tileGroupString);
    long numKantsu = countKantsu(tileGroupString);

    // Check for Iipeikou and Ryanpeikou
    int peikou = countBeikou(tileGroupStringNo4);
    if (peikou >= 2) {
      yakus.add(Yaku.Rianpeikou);
    } else if (peikou == 1) {
      yakus.add(Yaku.Ippeikou);
    }

    // Check for Sanankou (Three concealed triplets)
    if (numAnkou == 3) {
      yakus.add(Yaku.Sanankou);
    }

    // Check for Sankantsu (Three kans)
    if (numKantsu == 3) {
      yakus.add(Yaku.Sankantsu);
    }

    // Check for Yakuhai
    getYakuhaiYaku(tileGroupString, yakus, jihaiKoutsu, jihaiToitsu);

    // Compute Fu (excluding Chiitoitsu which is always 25 fu)
    if (!isChiitoitsu(tileGroupString)) {
      fu = computeFu(tileGroupString, isPinfu, tanki);
    }
    int fan = calculateFan(yakus);
    return new Pair<>(yakus, new Pair<>(fan, fu));
  }

  /**
   * Checks if the hand is a single wait (Tanki).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand is a single wait, false otherwise
   */
  private boolean isTanki(List<String> tileGroupString) {
    return tileGroupString.stream()
        .anyMatch(s -> s.charAt(2) == TileGroup.markToitsu && s.length() == 4);
  }

  /**
   * Checks if the hand is Chiitoitsu (Seven pairs).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand is Chiitoitsu, false otherwise
   */
  private boolean isChiitoitsu(List<String> tileGroupString) {
    return tileGroupString.size() == 7;
  }

  /**
   * Checks if the hand contains any Shunzi (sequences).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand contains any Shunzi, false otherwise
   */
  private boolean hasShunzi(List<String> tileGroupString) {
    return tileGroupString.stream()
        .anyMatch(s -> s.charAt(2) == TileGroup.markShuntsu);
  }

  /**
   * Checks if the hand is Toitoiho (All triplets).
   *
   * @param tileGroupString the list of tile group strings
   * @param hasShunzi       whether the hand contains any Shunzi
   * @return true if the hand is Toitoiho, false otherwise
   */
  private boolean isToitoiho(List<String> tileGroupString, boolean hasShunzi) {
    return !hasShunzi && tileGroupString.size() != 7;
  }

  /**
   * Checks if the hand is Tanyao (All simples).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand is Tanyao, false otherwise
   */
  private boolean isDuanYaoJiu(List<String> tileGroupString) {
    return tileGroupString.stream()
        .noneMatch(s -> Rule.isTaiYaochuuhaiOrJihai(s));
  }

  /**
   * Gets the Chinitsu and Honitsu status of the hand.
   *
   * @param tileGroupString the list of tile group strings
   * @param chinitsuColor   the color for Chinitsu
   * @return a pair containing the Chinitsu and Honitsu status
   */
  private Pair<Boolean, Boolean> getChinitsuHonitsu(
      List<String> tileGroupString, int chinitsuColor) {
    boolean isChinitsu = false;
    boolean isHonitsu = false;
    if (chinitsuColor == 0) {
      isChinitsu = tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markManzu);
      isHonitsu = !isChinitsu && tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markManzu || s.charAt(1) == TileGroup.markJihai);
    } else if (chinitsuColor == 1) {
      isChinitsu = tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markPinzu);
      isHonitsu = !isChinitsu && tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markPinzu || s.charAt(1) == TileGroup.markJihai);
    } else if (chinitsuColor == 2) {
      isChinitsu = tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markSouzu);
      isHonitsu = !isChinitsu && tileGroupString.stream()
          .allMatch(s -> s.charAt(1) == TileGroup.markSouzu || s.charAt(1) == TileGroup.markJihai);
    }
    return new Pair<>(isChinitsu, isHonitsu);
  }

  /**
   * Gets the Jihai Koutsu and Toitsu counts for the hand.
   *
   * @param tileGroupString the list of tile group strings
   * @return a pair containing the Jihai Koutsu and Toitsu counts
   */
  private Pair<boolean[], boolean[]> getJihaiKoutsuToitsu(List<String> tileGroupString) {
    boolean[] jihaiKoutsu = new boolean[7];
    boolean[] jihaiToitsu = new boolean[7];
    tileGroupString.forEach(s -> {
      if (s.charAt(1) == TileGroup.markJihai) {
        int index = s.charAt(0) - '1';
        if (s.charAt(2) == TileGroup.markKoutsu || s.charAt(2) == TileGroup.markKantsu) {
          jihaiKoutsu[index] = true;
        }
        if (s.charAt(2) == TileGroup.markToitsu) {
          jihaiToitsu[index] = true;
        }
      }
    });
    return new Pair<>(jihaiKoutsu, jihaiToitsu);
  }

  /**
   * Counts the number of Ankou (concealed triplets) in the hand.
   *
   * @param tileGroupString the list of tile group strings
   * @return the number of Ankou
   */
  private long countAnkou(List<String> tileGroupString) {
    return tileGroupString.stream()
        .filter(s -> (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu)
            || (s.length() == 4 && s.charAt(2) == TileGroup.markKoutsu
                && (s.charAt(3) == TileGroup.markTsumo1st || s.charAt(3) == TileGroup.markTsumo2nd
                    || s.charAt(3) == TileGroup.markTsumo3rd))
            ||
            (s.charAt(2) == TileGroup.markKantsu && s.charAt(3) == TileGroup.markAnkan))
        .count();
  }

  /**
   * Counts the number of Kantsu (quads) in the hand.
   *
   * @param tileGroupString the list of tile group strings
   * @return the number of Kantsu
   */
  private long countKantsu(List<String> tileGroupString) {
    return tileGroupString.stream()
        .filter(s -> s.charAt(2) == TileGroup.markKantsu)
        .count();
  }

  /**
   * Checks if the hand is Honroutou (All terminals and honors).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand is Honroutou, false otherwise
   */
  private boolean isHonroutou(List<String> tileGroupString) {
    return tileGroupString.stream()
        .allMatch(s -> Rule.isRoutouhai(s) || Rule.isJihai(s));
  }

  /**
   * Checks if the hand is Junchantaiyao (Pure outside hand).
   *
   * @param tileGroupString the list of tile group strings
   * @return true if the hand is Junchantaiyao, false otherwise
   */
  private boolean isJunchantaiyao(List<String> tileGroupString) {
    return tileGroupString.stream()
        .allMatch(Rule::isTaiYaochuuhai);
  }

  /**
   * Checks if the hand is Chantaiyao (Mixed outside hand).
   *
   * @param tileGroupString the list of tile group strings
   * @param isHonroutou     whether the hand is Honroutou
   * @param isJunchantaiyao whether the hand is Junchantaiyao
   * @return true if the hand is Chantaiyao, false otherwise
   */
  private boolean isChantaiyao(
      List<String> tileGroupString, boolean isHonroutou, boolean isJunchantaiyao) {
    return !isHonroutou && !isJunchantaiyao
        && tileGroupString.stream().allMatch(Rule::isTaiYaochuuhaiOrJihai);
  }

  /**
   * Checks if the hand is Pinfu (All sequences, no points).
   *
   * @param tileGroupString the list of tile group strings
   * @param tanki           whether the hand is a single wait
   * @return true if the hand is Pinfu, false otherwise
   */
  private boolean isPinfu(List<String> tileGroupString, boolean tanki) {
    if (!isMenzenchin || tanki) {
      return false;
    }
    if (tileGroupString.stream().anyMatch(
        s -> Rule.isYakuhaiToitsu(s, selfWind.toString(), prevalentWind.toString()) > 0)) {
      return false;
    }
    if (!tileGroupString.stream()
        .allMatch(s -> s.charAt(2) == TileGroup.markToitsu
            || s.charAt(2) == TileGroup.markShuntsu)) {
      return false;
    }
    if (!tileGroupString.stream().allMatch(s -> s.length() == 3
        || (s.charAt(3) != TileGroup.markTsumo2nd && s.charAt(3) != TileGroup.markRon2nd
            && (s.charAt(2) != TileGroup.markToitsu || s.charAt(3) != TileGroup.markRon1st)
            && ((s.charAt(3) != TileGroup.markTsumo3rd && s.charAt(3) != TileGroup.markRon3rd)
                || s.charAt(0) != '1')
            && ((s.charAt(3) != TileGroup.markTsumo1st && s.charAt(3) != TileGroup.markRon1st)
                || s.charAt(0) != '7')))) {
      return false;
    }
    return tileGroupString.size() == 5;
  }

  /**
   * Checks if the hand contains Sanshoku Doujun (Three color straight).
   *
   * @param tileGroupStringNo4 the list of tile group strings without the 4th
   *                           character
   * @return true if the hand contains Sanshoku Doujun, false otherwise
   */
  private boolean isSanshokuDoujun(List<String> tileGroupStringNo4) {
    String[] sanSeTongShunTiles = {
        "1mS", "2mS", "3mS", "4mS", "5mS", "6mS", "7mS",
        "1pS", "2pS", "3pS", "4pS", "5pS", "6pS", "7pS",
        "1sS", "2sS", "3sS", "4sS", "5sS", "6sS", "7sS"
    };
    for (int i = 0; i < 7; ++i) {
      if (tileGroupStringNo4.contains(sanSeTongShunTiles[i])
          && tileGroupStringNo4.contains(sanSeTongShunTiles[i + 7])
          && tileGroupStringNo4.contains(sanSeTongShunTiles[i + 14])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the hand contains Sanshoku Doukou (Three color triplets).
   *
   * @param tileGroupStringNo4 the list of tile group strings without the 4th
   *                           character
   * @return true if the hand contains Sanshoku Doukou, false otherwise
   */
  private boolean isSanshokuDoukou(List<String> tileGroupStringNo4) {
    String[] sanSeTongKeTiles = {
        "1mK", "2mK", "3mK", "4mK", "5mK", "6mK", "7mK", "8mK", "9mK",
        "1pK", "2pK", "3pK", "4pK", "5pK", "6pK", "7pK", "8pK", "9pK",
        "1sK", "2sK", "3sK", "4sK", "5sK", "6sK", "7sK", "8sK", "9sK"
    };
    for (int i = 0; i < 9; ++i) {
      if (tileGroupStringNo4.contains(sanSeTongKeTiles[i])
          && tileGroupStringNo4.contains(sanSeTongKeTiles[i + 9])
          && tileGroupStringNo4.contains(sanSeTongKeTiles[i + 18])) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the hand contains Ittsu (Straight).
   *
   * @param tileGroupStringNo4 the list of tile group strings without the 4th
   *                           character
   * @return true if the hand contains Ittsu, false otherwise
   */
  private boolean isIttsu(List<String> tileGroupStringNo4) {
    String[] ittsuM = { "1mS", "4mS", "7mS" };
    String[] ittsuP = { "1pS", "4pS", "7pS" };
    String[] ittsuS = { "1sS", "4sS", "7sS" };

    return Arrays.stream(ittsuM).allMatch(tileGroupStringNo4::contains)
        || Arrays.stream(ittsuP).allMatch(tileGroupStringNo4::contains)
        || Arrays.stream(ittsuS).allMatch(tileGroupStringNo4::contains);
  }

  /**
   * Counts the number of Beikou (double sequences) in the hand.
   *
   * @param tileGroupStringNo4 the list of tile group strings without the 4th
   *                           character
   * @return the number of Beikou
   */
  private int countBeikou(List<String> tileGroupStringNo4) {
    if (!isMenzenchin) {
      return 0;
    }
    String[] shuntsuPattern = {
        "1sS", "2sS", "3sS", "4sS", "5sS", "6sS", "7sS",
        "1pS", "2pS", "3pS", "4pS", "5pS", "6pS", "7pS",
        "1mS", "2mS", "3mS", "4mS", "5mS", "6mS", "7mS"
    };
    int peikou = 0;
    for (String tiles : shuntsuPattern) {
      long count = tileGroupStringNo4.stream()
          .filter(s -> s.equals(tiles))
          .count();
      if (count >= 2) {
        peikou++;
      }
    }
    return peikou;
  }

  /**
   * Adds Yakuhai Yaku to the given list if applicable.
   *
   * @param tileGroupString the list of tile group strings
   * @param yakus           the list of Yaku to add to
   * @param jihaiKoutsu     the array indicating which jihai Koutsu are present
   * @param jihaiToitsu     the array indicating which jihai Toitsu are present
   */
  private void getYakuhaiYaku(List<String> tileGroupString, List<Yaku> yakus, boolean[] jihaiKoutsu,
      boolean[] jihaiToitsu) {
    // 小三元
    if ((jihaiKoutsu[4] && jihaiKoutsu[5] && jihaiToitsu[6])
        || (jihaiKoutsu[4] && jihaiToitsu[5] && jihaiKoutsu[6])
        || (jihaiToitsu[4] && jihaiKoutsu[5] && jihaiKoutsu[6])) {
      yakus.add(Yaku.Shousangen);
    }

    // 役牌
    if (jihaiKoutsu[4]) {
      yakus.add(Yaku.YakuhaiHaku);
    }
    if (jihaiKoutsu[5]) {
      yakus.add(Yaku.YakuhaiHatsu);
    }
    if (jihaiKoutsu[6]) {
      yakus.add(Yaku.YakuhaiChu);
    }

    if (prevalentWind == BaseTile.windTon && jihaiKoutsu[0]) {
      yakus.add(Yaku.BakazeTon);
    }
    if (prevalentWind == BaseTile.windNan && jihaiKoutsu[1]) {
      yakus.add(Yaku.BakazeNan);
    }
    if (prevalentWind == BaseTile.windShaa && jihaiKoutsu[2]) {
      yakus.add(Yaku.BakazeShaa);
    }
    if (prevalentWind == BaseTile.windPei && jihaiKoutsu[3]) {
      yakus.add(Yaku.BakazePei);
    }

    if (selfWind == BaseTile.windTon && jihaiKoutsu[0]) {
      yakus.add(Yaku.JikazeTon);
    }
    if (selfWind == BaseTile.windNan && jihaiKoutsu[1]) {
      yakus.add(Yaku.JikazeNan);
    }
    if (selfWind == BaseTile.windShaa && jihaiKoutsu[2]) {
      yakus.add(Yaku.JikazeShaa);
    }
    if (selfWind == BaseTile.windPei && jihaiKoutsu[3]) {
      yakus.add(Yaku.JikazePei);
    }
  }

  /**
   * Computes the Fu (points) for the hand.
   *
   * @param tileGroupString the list of tile group strings
   * @param isPinfu         whether the hand is Pinfu
   * @param tanki           whether the hand is a single wait
   * @return the computed Fu
   */
  private int computeFu(List<String> tileGroupString, boolean isPinfu, boolean tanki) {
    int fu = 20;

    // Wait type
    if (tanki) {
      fu += 2;
    }
    if (tileGroupString.stream().anyMatch(
        s -> s.length() == 4
            && s.charAt(2) == TileGroup.markShuntsu
            && (s.charAt(3) == TileGroup.markTsumo2nd
                || s.charAt(3) == TileGroup.markRon2nd))) {
      fu += 2;
    }

    if (tileGroupString.stream()
        .anyMatch(s -> s.length() == 4 && s.charAt(2) == TileGroup.markShuntsu
            && ((s.charAt(0) == '1'
                && (s.charAt(3) == TileGroup.markTsumo3rd
                    || s.charAt(3) == TileGroup.markRon3rd))
                || (s.charAt(0) == '7'
                    && (s.charAt(3) == TileGroup.markTsumo1st
                        || s.charAt(3) == TileGroup.markRon1st))))) {
      fu += 2;
    }

    // Winning method
    if (tileGroupString.stream().anyMatch(
        s -> s.length() == 4
            && (s.charAt(3) == TileGroup.markTsumo1st
                || s.charAt(3) == TileGroup.markTsumo2nd
                || s.charAt(3) == TileGroup.markTsumo3rd))
        && !isPinfu) {
      fu += 2;
    }

    if (tileGroupString.stream().anyMatch(s -> s.length() == 4
        && (s.charAt(3) == TileGroup.markRon1st
            || s.charAt(3) == TileGroup.markRon2nd
            || s.charAt(3) == TileGroup.markRon3rd))
        && isMenzenchin) {
      fu += 10;
    }

    // Pair Fu
    for (String s : tileGroupString) {
      fu += Rule.isYakuhaiToitsu(s, selfWind.toString(), prevalentWind.toString()) > 0 ? 2 : 0;
    }

    // Meld Fu
    for (String s : tileGroupString) {
      if (s.length() == 3 && s.charAt(2) == TileGroup.markKoutsu) {
        fu += Rule.isTaiYaochuuhai(s) ? 8 : 4;
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
                fu += Rule.isTaiYaochuuhai(s) ? 8 : 4;
                break;
              case TileGroup.markRon1st:
              case TileGroup.markRon2nd:
              case TileGroup.markRon3rd:
              case TileGroup.markFuuro:
                fu += Rule.isTaiYaochuuhai(s) ? 4 : 2;
                break;
              default:
                break;
            }
            break;
          case TileGroup.markKantsu:
            if (s.charAt(3) == TileGroup.markMinkan) {
              fu += Rule.isTaiYaochuuhai(s) ? 16 : 8;
            } else if (s.charAt(3) == TileGroup.markAnkan) {
              fu += Rule.isTaiYaochuuhai(s) ? 32 : 16;
            }
            break;
          default:
            break;
        }
      }
    }

    // Adjust Fu
    if (tileGroupString.stream().anyMatch(
        s -> s.length() == 4
            && (s.charAt(3) == TileGroup.markRon1st
                || s.charAt(3) == TileGroup.markRon2nd
                || s.charAt(3) == TileGroup.markRon3rd))
        && !isMenzenchin) {
      if (fu == 20) {
        fu = 30;
      }
    }

    // Pinfu Tsumo is 20 Fu
    if (tileGroupString.stream().anyMatch(
        s -> s.length() == 4 && (s.charAt(3) == TileGroup.markTsumo1st
            || s.charAt(3) == TileGroup.markTsumo2nd || s.charAt(3) == TileGroup.markTsumo3rd))
        && isPinfu) {
      fu = 20;
    }

    if (fu % 10 != 0) {
      fu = ((fu / 10) + 1) * 10;
    }

    return fu;
  }

  /**
   * Calculates the total Fan (multipliers) for the given list of Yaku.
   *
   * @param yakus the list of Yaku
   * @return the total Fan
   */
  private int calculateFan(List<Yaku> yakus) {
    int fan = 0;
    for (Yaku yaku : yakus) {
      fan += yaku.getFan().getFanValue();
    }
    return fan;
  }

  /**
   * Compares two pairs of Fan and Fu.
   *
   * @param fanFu1 the first pair of Fan and Fu
   * @param fanFu2 the second pair of Fan and Fu
   * @return a negative integer, zero, or a positive integer as the first pair is
   *         less than, equal to, or greater than the second
   */
  private int compareFanFu(Pair<Integer, Integer> fanFu1, Pair<Integer, Integer> fanFu2) {
    int fan1 = fanFu1.getFst();
    int fan2 = fanFu2.getFst();
    if (fan1 != fan2) {
      return Integer.compare(fan1, fan2);
    }
    return Integer.compare(fanFu1.getSnd(), fanFu2.getSnd());
  }

  /**
   * Gets the maximum Yaku and Fan/Fu for the given completed tiles and call
   * groups.
   *
   * @param ct         the completed tiles
   * @param callGroups the list of call groups
   * @return a pair containing the list of Yaku and a pair of Fan and Fu
   */
  private Pair<List<Yaku>, Pair<Integer, Integer>> getMaxYakuAndFanFu(
      CompletedTiles ct, List<CallGroup> callGroups) {
    boolean isTsumo = playerStats.isTsumo();
    Pair<List<Yaku>, Pair<Integer, Integer>> maxYakuFanFu = new Pair<>(
        new ArrayList<>(), new Pair<>(0, 0));
    int maxFan = 0;
    List<List<String>> tileGroupStrings = TileGroup.generateTileGroupStrings(
        ct, callGroups, isTsumo, winningTile);

    for (List<String> tileGroup : tileGroupStrings) {
      List<Yaku> yakus = getYakuman(tileGroup);
      if (hasYakuman) {
        int fan = calculateFan(yakus);
        if (fan > maxFan) {
          maxYakuFanFu = new Pair<>(new ArrayList<>(yakus), new Pair<>(fan, 20));
        }
      } else {
        Pair<List<Yaku>, Pair<Integer, Integer>> yakuFanFu = getYakuAndFanFu(tileGroup);
        Pair<Integer, Integer> fanFu = yakuFanFu.getSnd();
        if (compareFanFu(fanFu, maxYakuFanFu.getSnd()) > 0) {
          maxYakuFanFu = yakuFanFu;
        }
      }
    }
    return maxYakuFanFu;
  }

  /**
   * Adds Dora Yaku to the given list if applicable.
   *
   * @param yakuList the list of Yaku to add to
   */
  private void getDoraYaku(List<Yaku> yakuList) {
    for (Tile t : playerStats.getHand()) {
      if (t.isDora()) {
        yakuList.add(Yaku.Dora);
      }
      // if (t.isRedDora()) {
      // yakuList.add(Yaku.Akadora);
      // } ABOLISHED
      if (t.isUraDora()) {
        yakuList.add(Yaku.Uradora);
      }
    }
    for (int t = 0; t < playerStats.getNumAkaDora(); t++) {
      yakuList.add(Yaku.Akadora);
    }
  }

  /**
   * Counts the Yaku for the player's hand.
   *
   * @return a pair containing the list of Yaku and a pair of Fan and Fu
   */
  public Pair<List<Yaku>, Pair<Integer, Integer>> yakuCounter() {
    List<Yaku> maxYakuList = new ArrayList<>();
    Pair<List<Yaku>, Pair<Integer, Integer>> maxYakuFanFu = new Pair<>(
        new ArrayList<>(), new Pair<>(0, 0));
    getSepcialYakuman(maxYakuList);
    if (!hasSpecialYaku) {
      // Decompose the tiles (already unique)
      List<CompletedTiles> completeTilesList = new ArrayList<>();
      Algorithm.mergeInto(completeTilesList, CompletedTiles.getCompletedTiles(originalHand));

      /* Count Seven Pairs */
      if (!hasYakuman && Rule.is7ToitsuShape(originalHand)) {
        CompletedTiles ct = new CompletedTiles();
        System.out.println(ct.getBody().size());
        for (int i = 0; i < 14; i += 2) {
          TileGroup tg = new TileGroup(TileGroup.Type.TOITSU);
          tg.setTiles(List.of(originalHand.get(i), originalHand.get(i)));
          ct.getBody().add(tg);
        }
        // if not set a fake head, we will get a NullPointerException
        TileGroup tg = new TileGroup(TileGroup.Type.TOITSU);
        ct.setHead(tg);
        completeTilesList.add(ct);
        System.out.println(completeTilesList);
      }
      
      // Next

      for (CompletedTiles cts : completeTilesList) {
        Pair<List<Yaku>, Pair<Integer, Integer>> yakuFanFus = getMaxYakuAndFanFu(cts,
            playerStats.getCallGroups());
        maxYakuFanFu = compareFanFu(
            maxYakuFanFu.getSnd(), yakuFanFus.getSnd()) > 0 ? maxYakuFanFu : yakuFanFus;
      }
    }

    if (!hasYakuman) {
      getKouiYaku(maxYakuList);
      getGuuzenYaku(maxYakuList);
      getDoraYaku(maxYakuList);
    }

    Algorithm.mergeInto(maxYakuFanFu.getFst(), maxYakuList);

    if (!Rule.canAgari(maxYakuFanFu.getFst())) {
      // 说明无役
      maxYakuFanFu = new Pair<>(new ArrayList<>(), new Pair<>(0, 0));
      maxYakuFanFu.getFst().add(Yaku.None);
    }

    maxYakuFanFu.getFst().sort(null);

    return new Pair<>(maxYakuFanFu.getFst(),
        new Pair<>(maxYakuFanFu.getSnd().getFst() + calculateFan(maxYakuList),
            maxYakuFanFu.getSnd().getSnd()));
  }

  /**
   * Checks if the player has a Yakuman (highest value hand).
   *
   * @return true if the player has a Yakuman, false otherwise
   */
  public boolean hasYakuman() {
    return hasYakuman;
  }
}