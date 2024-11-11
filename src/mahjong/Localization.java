package mahjong;

import java.util.List;

public class Localization {
    
    public static String yakuToString(Yaku yaku) {
        switch (yaku) {
            case None: return "None";
            case Riichi: return "Riichi";
            case Tanyao: return "Tanyao";
            case Menzentsumo: return "Menzenchin tsumohou";
            case Jikaze_Ton: return "Self-wind: East";
            case Jikaze_Nan: return "Self-wind: South";
            case Jikaze_Sha: return "Self-wind: West";
            case Jikaze_Pei: return "Self-wind: North";
            case Bakaze_Ton: return "Prevailing wind: East";
            case Bakaze_Nan: return "Prevailing wind: South";
            case Bakaze_Sha: return "Prevailing wind: West";
            case Bakaze_Pei: return "Prevailing wind: North";
            case Yakuhai_Haku: return "Yakuhai: White Dragon";
            case Yakuhai_Hatsu: return "Yakuhai: Green Dragon";
            case Yakuhai_Chu: return "Yakuhai: Red Dragon";
            case Pinfu: return "Pinfu";
            case Ippeikou: return "Iipeikou";
            case Chankan: return "Chankan";
            case Rinshankaihou: return "Rinshankaihou";
            case Haiteiraoyue: return "Haiteiraoyue";
            case Houteiraoyu: return "Hetairaoyu";
            case Ippatsu: return "Ippatsu";
            case Dora: return "Dora";
            case Uradora: return "Ura Dora";
            case Akadora: return "Aka Dora";
            case Peidora: return "Pei Dora";
            case Honchantaiyaochu_Naki: return "Honchantaiyaochu (Naki)";
            case Ikkitsuukan_Naki: return "Ittsu: 1-9 (Naki)";
            case Sanshokudoujun_Naki: return "Sanshoku doujun (Naki)";
            case Yaku_1_han: return "1 Han";
            case Dabururiichi: return "Double Riichi";
            case Sanshokudoukou: return "Sanshoku doukou";
            case Sankantsu: return "Sankantsu";
            case Toitoiho: return "Toitoi";
            case Sanankou: return "Sanankou";
            case Shousangen: return "Shousangen";
            case Honroutou: return "Honroutou";
            case Chiitoitsu: return "Chiitoitsu";
            case Honchantaiyaochu: return "Honchantaiyaochu";
            case Ikkitsuukan: return "Ittsu: 1-9";
            case Sanshokudoujun: return "Sanshoku doujun";
            case Junchantaiyaochu_Naki: return "Junchant";
            case Honitsu_Naki: return "Honitsu (Naki)";
            case Yaku_2_han: return "2 Han";
            case Rianpeikou: return "Ryanpeikou";
            case Junchantaiyaochu: return "Junchant";
            case Honitsu: return "Honitsu";
            case Yaku_3_han: return "3 Han";
            case Chinitsu_Naki: return "Chinitsu (Naki)";
            case Yaku_5_han: return "5 Han";
            case Chinitsu: return "Chinitsu";
            case Yaku_6_han: return "6 Han";
            case Nagashimangan: return "Nagashi Mangan";
            case Yaku_mangan: return "Mangan";
            case Tenhou: return "Tenhou";
            case Chiihou: return "Chiihou";
            case Daisangen: return "Daisangen";
            case Suuankou: return "Suuankou";
            case Tsuuiisou: return "Tsuuiisou";
            case Ryuiisou: return "Ryuiisou";
            case Chinroutou: return "Chinroutou";
            case Kokushimusou: return "Kokushi Musou";
            case Shousuushii: return "Shousuushii";
            case Suukantsu: return "Suukantsu";
            case Chuurenpoutou: return "Chuuren Poutou";
            case Yakuman: return "Yakuman";
            case Suuankou_1: return "Suuankou Tanki";
            case Koukushimusou_13: return "Kokushi Musou 13men machi";
            case Chuurenpoutou_9: return "Chuuren Poutou 9men machi";
            case Daisuushii: return "Daisuushii";
            case Yakuman_Double: return "Double Yakuman";
            default: return yaku.name();
        }
    }

    public static String yakusToString(List<Yaku> yakus) {
        StringBuilder sb = new StringBuilder("|");
        for (Yaku yaku : yakus) {
            sb.append(yakuToString(yaku)).append("|");
        }
        return sb.toString();
    }
    
}
