package mahjong;

import java.util.List;

import javax.management.RuntimeErrorException;

public class ScoreCounter {
    public void initialize(int fan, int fu) {
        this.fan = fan;
        this.fu = fu;
    }
    /**
     * Calculates and registers the score based on the given fan and fu values.
     *
     * @param oya   A boolean indicating if the player is the dealer (true) or not (false).
     * @param tsumo A boolean indicating if the win was by tsumo (true) or ron (false).
     *
     * The method uses the fan and fu values to determine the score according to the rules of Japanese Mahjong.
     * It calls the registerScore method with the appropriate parameters based on the fan and fu values.
     * 
     * The scoring is categorized as follows:
     * - Yakuman (fan > 13)
     * - Sanbaiman (fan >= 11)
     * - Baiman (fan >= 8)
     * - Haneman (fan >= 6)
     * - Mangan (fan == 5 or fan == 4 with fu >= 40)
     * - Various other combinations for fan values 4, 3, 2, and 1 with different fu values.
     *
     * If the combination of fan and fu is not supported, a runtime error is thrown.
     */
    public void fanfuToScore(boolean oya, boolean tsumo) {
        if (fan > 13) {
            // if we have achieved a Yakuman
            registerScore(oya, tsumo,  48000 * (fan / 13), 16000  * (fan / 13), 32000  * (fan / 13), 16000  * (fan / 13), 8000  * (fan / 13));
        }
        else if (fan >= 11) {
            // if we have achieved a Sanbaiman
            registerScore(oya, tsumo,  36000, 12000, 24000, 12000, 6000);
        }
        else if (fan >= 8) {
            // if we have achieved a Baiman
            registerScore(oya, tsumo,  24000, 8000, 16000, 8000, 4000);
        }
        else if (fan >= 6) {
            // if we have achieved a Haneman
            registerScore(oya, tsumo, 18000, 6000, 12000, 6000, 3000);
        }
        
		else if (fan == 5)
		{
			registerScore(oya, tsumo, 12000, 4000, 8000, 4000, 2000);
		}
		else if (fan == 4)
		{
			// 40符以上满贯
			if (fu >= 40)
			{
				registerScore(oya, tsumo, 12000, 4000, 8000, 4000, 2000);
			}
			else if (fu == 30)
			{
				registerScore(oya, tsumo, 11600, 3900, 7700, 3900, 2000);
			}
			else if (fu == 25)
			{
				registerScore(oya, tsumo, 9600, 3200, 6400, 3200, 1600);
			}
			else if (fu == 20)
			{
				registerScore(oya, tsumo, 7700, 2600, 5200, 2600, 1300);
			}
		}
		else if (fan == 3)
		{
			// 70符以上满贯
			if (fu >= 70)
			{
				registerScore(oya, tsumo, 12000, 4000, 8000, 4000, 2000);
			}
			else if (fu == 60)
			{
				registerScore(oya, tsumo, 11600, 3900, 7700, 3900, 2000);
			}
			else if (fu == 50)
			{
				registerScore(oya, tsumo, 9600, 3200, 6400, 3200, 1600);
			}
			else if (fu == 40)
			{
				registerScore(oya, tsumo, 7700, 2600, 5200, 2600, 1300);
			}
			else if (fu == 30)
			{
				registerScore(oya, tsumo, 5800, 2000, 3900, 2000, 1000);
			}
			else if (fu == 25)
			{
				registerScore(oya, tsumo, 4800, 1600, 3200, 1600, 800);
			}
			else if (fu == 20)
			{
				registerScore(oya, tsumo, 3900, 1300, 2600, 1300, 700);
			}
		}
		else if (fan == 2)
		{
			if (fu >= 110)
			{
				registerScore(oya, tsumo, 10600, 3600, 7100, 3600, 1800);
			}
			else if (fu == 100)
			{
				registerScore(oya, tsumo, 9600, 3200, 6400, 3200, 1600);
			}
			else if (fu == 90)
			{
				registerScore(oya, tsumo, 8700, 2900, 5800, 2900, 1500);
			}
			else if (fu == 80)
			{
				registerScore(oya, tsumo, 7700, 2600, 5200, 2600, 1300);
			}
			else if (fu == 70)
			{
				registerScore(oya, tsumo, 6800, 2300, 4500, 2300, 1200);
			}
			else if (fu == 60)
			{
				registerScore(oya, tsumo, 5800, 2000, 3900, 2000, 1000);
			}
			else if (fu == 50)
			{
				registerScore(oya, tsumo, 4800, 1600, 3200, 1600, 800);
			}
			else if (fu == 40)
			{
				registerScore(oya, tsumo, 3900, 1300, 2600, 1300, 700);
			}
			else if (fu == 30)
			{
				registerScore(oya, tsumo, 2900, 1000, 2000, 1000, 500);
			}
			else if (fu == 25)
			{
				registerScore(oya, tsumo, 2400, -1, 1600, -1, -1);
			}
			else if (fu == 20)
			{
				registerScore(oya, tsumo, 2000, 700, 1300, 700, 400);
			}
		}
		else if (fan == 1)
		{
			if (fu >= 110)
			{
				registerScore(oya, tsumo, 5300, -1, 3600, -1, -1);
			}
			else if (fu == 100)
			{
				registerScore(oya, tsumo, 4800, 1600, 3200, 1600, 800);
			}
			else if (fu == 90)
			{
				registerScore(oya, tsumo, 4400, 1500, 2900, 1500, 800);
			}
			else if (fu == 80)
			{
				registerScore(oya, tsumo, 3900, 1300, 2600, 1300, 700);
			}
			else if (fu == 70)
			{
				registerScore(oya, tsumo, 3400, 1200, 2300, 1200, 600);
			}
			else if (fu == 60)
			{
				registerScore(oya, tsumo, 2900, 1000, 2000, 1000, 500);
			}
			else if (fu == 50)
			{
				registerScore(oya, tsumo, 2400, 800, 1600, 800, 400);
			}
			else if (fu == 40)
			{
				registerScore(oya, tsumo, 2000, 700, 1300, 700, 400);
			}
			else if (fu == 30)
			{
				registerScore(oya, tsumo, 1500, 500, 1000, 500, 300);
			}
			else if (fu == 20)
			{
				registerScore(oya, tsumo, 1000, -1, -1, -1, -1);
			}
		}
		throw new RuntimeErrorException("Exception message");
    }

    private void registerScore(boolean oya, boolean tsumo, int scoreParentRon, int scoreParentTsumoAll,
            int scoreChildRon, int scoreChildTsumoParent, int scoreChildTsumoChild) {
        if (oya) {
            if (tsumo) {
                score1 = scoreParentTsumoAll;
            } else {
                score1 = scoreParentRon;
            }
        } else {
            if (tsumo) {
                score1 = scoreChildTsumoParent;
                score2 = scoreChildTsumoChild;
            } else {
                score1 = scoreChildRon;
            }
        }
        return;
    }

    public static boolean canAgari(List<Yaku> yakus) {
        for (Yaku yaku : yakus) {
            if (yaku != Yaku.None &&
                yaku != Yaku.Dora &&
                yaku != Yaku.Akadora &&
                yaku != Yaku.Uradora &&
                yaku != Yaku.Peidora) {
                return true;
            }
        }
        return false;
    }

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

    private int fan;
    private int fu;
}
