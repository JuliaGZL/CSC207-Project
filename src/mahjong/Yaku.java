package mahjong;

/**
 * An enumeration of Yaku (役), which are the scoring elements in Mahjong.
 */
public enum Yaku {
  /** Represents no Yaku (null value). */
  None,

  /** Riichi (立直): Closed ready hand declared with a bet. */
  Riichi,
  /** Tanyao (断么九): All simples; hand without terminals or honors. */
  Tanyao,
  /** Menzen Tsumo (門前清自摸和): Concealed self-draw win. */
  Menzentsumo,
  /** Jikaze Ton (自風 東): Seat wind East. */
  JikazeTon,
  /** Jikaze Nan (自風 南): Seat wind South. */
  JikazeNan,
  /** Jikaze Sha (自風 西): Seat wind West. */
  JikazeShaa,
  /** Jikaze Pei (自風 北): Seat wind North. */
  JikazePei,
  /** Bakaze Ton (場風 東): Round wind East. */
  BakazeTon,
  /** Bakaze Nan (場風 南): Round wind South. */
  BakazeNan,
  /** Bakaze Sha (場風 西): Round wind West. */
  BakazeShaa,
  /** Bakaze Pei (場風 北): Round wind North. */
  BakazePei,
  /** Yakuhai Haku (役牌 白): White Dragon honor tile. */
  YakuhaiHaku,
  /** Yakuhai Hatsu (役牌 發): Green Dragon honor tile. */
  YakuhaiHatsu,
  /** Yakuhai Chun (役牌 中): Red Dragon honor tile. */
  YakuhaiChu,
  /** Pinfu (平和): No points hand. */
  Pinfu,
  /** Iipeikou (一盃口): One set of identical sequences. */
  Ippeikou,
  /** Chankan (槍槓): Robbing a kan. */
  Chankan,
  /** Rinshankaihou (嶺上開花): Winning on a dead wall draw. */
  Rinshankaihou,
  /** Haiteiraoyue (海底撈月): Winning on the last tile of the wall. */
  Haiteiraoyue,
  /** Houteiraoyu (河底撈魚): Winning on the last discard. */
  Houteiraoyu,
  /** Ippatsu (一発): Winning within one turn after declaring riichi. */
  Ippatsu,
  /** Dora (ドラ): Bonus tile. */
  Dora,
  /** Uradora (裏ドラ): Hidden bonus tile. */
  Uradora,
  /** Akadora (赤ドラ): Red bonus tile. */
  Akadora,
  /** Peidora (北ドラ): North bonus tile (only in 3-player Mahjong). */
  Peidora,
  /** Honchantaiyaochu Naki (混全帯么九 鳴き): Mixed outside hand with open melds. */
  HonchantaiyaochuNaki,
  /** Ikkitsuukan Naki (一気通貫 鳴き): Pure straight with open melds. */
  IkkitsuukanNaki,
  /** Sanshokudoujun Naki (三色同順 鳴き): Three color straight with open melds. */
  SanshokudoujunNaki,

  /** Yaku 1 han (役 1 翻): One han yaku. */
  Yaku1han,

  /** Dabururiichi (ダブル立直): Double riichi. */
  Dabururiichi,
  /** Sanshokudoukou (三色同刻): Three color triplets. */
  Sanshokudoukou,
  /** Sankantsu (三槓子): Three kans. */
  Sankantsu,
  /** Toitoiho (対々和): All triplets. */
  Toitoiho,
  /** Sanankou (三暗刻): Three concealed triplets. */
  Sanankou,
  /** Shousangen (小三元): Little three dragons. */
  Shousangen,
  /** Honroutou (混老頭): All terminals and honors. */
  Honroutou,
  /** Chiitoitsu (七対子): Seven pairs. */
  Chiitoitsu,
  /** Honchantaiyaochu (混全帯么九): Mixed outside hand. */
  Honchantaiyaochu,
  /** Ikkitsuukan (一気通貫): Pure straight. */
  Ikkitsuukan,
  /** Sanshokudoujun (三色同順): Three color straight. */
  Sanshokudoujun,
  /** Junchantaiyaochu Naki (純全帯么九 鳴き): Pure outside hand with open melds. */
  JunchantaiyaochuNaki,
  /** Honitsu Naki (混一色 鳴き): Half flush with open melds. */
  HonitsuNaki,

  /** Yaku 2 han (役 2 翻): Two han yaku. */
  Yaku2han,

  /** Rianpeikou (二盃口): Two sets of identical sequences. */
  Rianpeikou,
  /** Junchantaiyaochu (純全帯么九): Pure outside hand. */
  Junchantaiyaochu,
  /** Honitsu (混一色): Half flush. */
  Honitsu,

  /** Yaku 3 han (役 3 翻): Three han yaku. */
  Yaku3han,

  /** Chinitsu Naki (清一色 鳴き): Full flush with open melds. */
  ChinitsuNaki,

  /** Yaku 5 han (役 5 翻): Five han yaku. */
  Yaku5han,

  /** Chinitsu (清一色): Full flush. */
  Chinitsu,

  /** Yaku 6 han (役 6 翻): Six han yaku. */
  Yaku6han,

  /** Nagashimangan (流し満貫): Mangan at draw. */
  Nagashimangan,

  /** Yaku mangan (役 満貫): Mangan yaku. */
  Yakumangan,

  /** Tenhou (天和): Heavenly hand. */
  Tenhou,
  /** Chiihou (地和): Earthly hand. */
  Chiihou,
  /** Daisangen (大三元): Big three dragons. */
  Daisangen,
  /** Suuankou (四暗刻): Four concealed triplets. */
  Suuankou,
  /** Tsuuiisou (字一色): All honors. */
  Tsuuiisou,
  /** Ryuiisou (緑一色): All green. */
  Ryuiisou,
  /** Chinroutou (清老頭): All terminals. */
  Chinroutou,
  /** Koukushimusou (国士無双): Thirteen orphans. */
  Koukushimusou,
  /** Shousuushii (小四喜): Little four winds. */
  Shousuushii,
  /** Suukantsu (四槓子): Four kans. */
  Suukantsu,
  /** Chuurenpoutou (九蓮宝燈): Nine gates. */
  Chuurenpoutou,

  /** Yakuman (役満): Limit hand. */
  Yakuman,

  /** Suuankou 1 (四暗刻 単騎): Single wait four concealed triplets. */
  Suuankou1,
  /** Koukushimusou 13 (国士無双 13面待ち): Thirteen-sided wait thirteen orphans. */
  Koukushimusou13,
  /** Chuurenpoutou 9 (九蓮宝燈 九面待ち): Nine-sided wait nine gates. */
  Chuurenpoutou9,
  /** Daisuushii (大四喜): Big four winds. */
  Daisuushii,

  /** Yakuman Double (倍役満): Double limit hand. */
  YakumanDouble;

  /**
   * Localization object for translating tile identifiers to localized strings.
   */
  private static Localization<Yaku> localization = new Localization<>(Yaku::valueOf);

  static {
    localization.initializeTranslator("..\\localization\\yakuslenglish.yaml");
  }

  /**
   * Returns the corresponding fans of given Yaku.
   *
   * @return the corresponding fans of given Yaku.
   */
  public Yaku getFan() {
    if (this == None) {
      return None;
    } else if (this.ordinal() > Yakuman.ordinal()) {
      return YakumanDouble;
    } else if (this.ordinal() > Yakumangan.ordinal()) {
      return Yakuman;
    } else if (this.ordinal() > Yaku6han.ordinal()) {
      return Yakumangan;
    } else if (this.ordinal() > Yaku5han.ordinal()) {
      return Yaku6han;
    } else if (this.ordinal() > Yaku3han.ordinal()) {
      return Yaku5han;
    } else if (this.ordinal() > Yaku2han.ordinal()) {
      return Yaku3han;
    } else if (this.ordinal() > Yaku1han.ordinal()) {
      return Yaku2han;
    } else {
      return Yaku1han;
    }
  }

  /**
   * Returns the localized string representation of this base tile.
   *
   * @return the localized name of the tile
   */
  @Override
  public String toString() {
    return localization.toString(this) + ": " + localization.toString(this.getFan());
  }
}
