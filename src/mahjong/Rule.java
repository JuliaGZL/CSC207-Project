package mahjong;

import java.util.List;

public class Rule {

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

  

}
