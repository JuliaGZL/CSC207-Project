package mahjong;

import java.util.Arrays;
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

  public static boolean isJihai(String s) {
    return s.charAt(1) == 'z';
  }

  public static boolean isTaiYaochuuhai(String s) {
    if (isJihai(s)) {
      return false;
    }
    if (s.charAt(2) == 'K' || s.charAt(2) == ':' || s.charAt(2) == '|') {
      return s.charAt(0) == '1' || s.charAt(0) == '9';
    }
    if (s.charAt(2) == 'S') {
      return s.charAt(0) == '1' || s.charAt(0) == '7';
    }
    return false;
  }

  public static boolean isRoutouhai(String s) {
    if (isJihai(s)) {
      return false;
    }
    if (s.charAt(2) == 'K' || s.charAt(2) == ':' || s.charAt(2) == '|') {
      return s.charAt(0) == '1' || s.charAt(0) == '9';
    }
    return false;
  }

  public static boolean isGreenpai(String s) {
    String[] greenTypes = new String[] { "2sK", "3sK", "4sK", "2sS", "6sK", "8sK", "6zK",
        "2s:", "3s:", "4s:", "6s:", "8s:", "6z:" };
    return Arrays.asList(greenTypes).contains(s);
  }

  public static boolean isTaiYaochuuhaiOrJihai(String s) {
    return isJihai(s) || isTaiYaochuuhai(s);
  }

  public static int isYakuhaiPair(String s, String selfWind, String prevalentWind) {
    if (s.charAt(2) != ':') {
      return 0;
    }
    if (s.charAt(1) != 'z') {
      return 0;
    }

    int cases = 0;
    if (s.equals(selfWind)) {
      cases++;
    }
    if (s.equals(selfWind)) {
      cases++;
    }
    if (s.charAt(0) - '1' >= 4) {
      cases++;
    }
    return cases;
  }

}
