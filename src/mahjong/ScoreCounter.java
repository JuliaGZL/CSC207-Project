package mahjong;

import java.util.ArrayList;

/**
 * The ScoreCounter class calculates and registers the score in a Mahjong game
 * based on the given fan and fu values.
 */
public class ScoreCounter {
  /**
   * Initializes the score counter with specified fan, fu, and player status.
   *
   * @param fan     the number of fan achieved
   * @param fu      the number of fu achieved
   * @param isOya   true if the player is the dealer (oya), false otherwise
   * @param isTsumo true if the win was by tsumo, false if by ron
   */
  public void initialize(int fan, int fu, boolean isOya, boolean isTsumo) {
    this.fan = fan;
    this.fu = fu;
    this.isOya = isOya;
    this.isTsumo = isTsumo;
  }

  /**
   * Calculates and registers the score based on the fan and fu values according
   * to Japanese Mahjong rules.
   * Throws a RuntimeException if the combination of fan and fu is not supported.
   */
  public void fanfuToScore() {
    if (fan > 13) {
      // if we have achieved a Yakuman
      registerScore(48000 * (fan / 13), 16000 * (fan / 13), 32000 * (fan / 13), 16000 * (fan / 13),
          8000 * (fan / 13));
    } else if (fan >= 11) {
      // if we have achieved a Sanbaiman
      registerScore(36000, 12000, 24000, 12000, 6000);
    } else if (fan >= 8) {
      // if we have achieved a Baiman
      registerScore(24000, 8000, 16000, 8000, 4000);
    } else if (fan >= 6) {
      // if we have achieved a Haneman
      registerScore(18000, 6000, 12000, 6000, 3000);
    } else if (fan == 5) {
      // if we have achieved a Mangan
      registerScore(12000, 4000, 8000, 4000, 2000);
    } else if (fan == 4) {
      // 40 fus above is Mangan
      if (fu >= 40) {
        registerScore(12000, 4000, 8000, 4000, 2000);
      } else if (fu == 30) {
        registerScore(11600, 3900, 7700, 3900, 2000);
      } else if (fu == 25) {
        registerScore(9600, 3200, 6400, 3200, 1600);
      } else if (fu == 20) {
        registerScore(7700, 2600, 5200, 2600, 1300);
      }
    } else if (fan == 3) {
      // 70 fus above is Mangan
      if (fu >= 70) {
        registerScore(12000, 4000, 8000, 4000, 2000);
      } else if (fu == 60) {
        registerScore(11600, 3900, 7700, 3900, 2000);
      } else if (fu == 50) {
        registerScore(9600, 3200, 6400, 3200, 1600);
      } else if (fu == 40) {
        registerScore(7700, 2600, 5200, 2600, 1300);
      } else if (fu == 30) {
        registerScore(5800, 2000, 3900, 2000, 1000);
      } else if (fu == 25) {
        registerScore(4800, 1600, 3200, 1600, 800);
      } else if (fu == 20) {
        registerScore(3900, 1300, 2600, 1300, 700);
      }
    } else if (fan == 2) {
      if (fu >= 110) {
        registerScore(10600, 3600, 7100, 3600, 1800);
      } else if (fu == 100) {
        registerScore(9600, 3200, 6400, 3200, 1600);
      } else if (fu == 90) {
        registerScore(8700, 2900, 5800, 2900, 1500);
      } else if (fu == 80) {
        registerScore(7700, 2600, 5200, 2600, 1300);
      } else if (fu == 70) {
        registerScore(6800, 2300, 4500, 2300, 1200);
      } else if (fu == 60) {
        registerScore(5800, 2000, 3900, 2000, 1000);
      } else if (fu == 50) {
        registerScore(4800, 1600, 3200, 1600, 800);
      } else if (fu == 40) {
        registerScore(3900, 1300, 2600, 1300, 700);
      } else if (fu == 30) {
        registerScore(2900, 1000, 2000, 1000, 500);
      } else if (fu == 25) {
        registerScore(2400, -1, 1600, -1, -1);
      } else if (fu == 20) {
        registerScore(2000, 700, 1300, 700, 400);
      }
    } else if (fan == 1) {
      if (fu >= 110) {
        registerScore(5300, -1, 3600, -1, -1);
      } else if (fu == 100) {
        registerScore(4800, 1600, 3200, 1600, 800);
      } else if (fu == 90) {
        registerScore(4400, 1500, 2900, 1500, 800);
      } else if (fu == 80) {
        registerScore(3900, 1300, 2600, 1300, 700);
      } else if (fu == 70) {
        registerScore(3400, 1200, 2300, 1200, 600);
      } else if (fu == 60) {
        registerScore(2900, 1000, 2000, 1000, 500);
      } else if (fu == 50) {
        registerScore(2400, 800, 1600, 800, 400);
      } else if (fu == 40) {
        registerScore(2000, 700, 1300, 700, 400);
      } else if (fu == 30) {
        registerScore(1500, 500, 1000, 500, 300);
      } else if (fu == 20) {
        registerScore(1000, -1, -1, -1, -1);
      }
    }
    throw new RuntimeException("Exception message");
  }

  /**
   * Registers the calculated scores based on the player's role and the type of
   * win.
   *
   * @param scoreParentRon        the score when the dealer wins by ron
   * @param scoreParentTsumoAll   the score when the dealer wins by tsumo
   *                              (collected from all players)
   * @param scoreChildRon         the score when a non-dealer wins by ron
   * @param scoreChildTsumoParent the score paid by the dealer when a non-dealer
   *                              wins by tsumo
   * @param scoreChildTsumoChild  the score paid by other non-dealers when a
   *                              non-dealer wins by tsumo
   */
  private void registerScore(int scoreParentRon, int scoreParentTsumoAll,
      int scoreChildRon, int scoreChildTsumoParent, int scoreChildTsumoChild) {
    this.scoreParentTsumoAll = scoreParentTsumoAll;
    this.scoreParentRon = scoreParentRon;

    this.scoreChildTsumoParent = scoreChildTsumoParent;
    this.scoreChildTsumoChild = scoreChildTsumoChild;
    this.scoreChildRon = scoreChildRon;
    return;
  }

  /**
   * Retrieves the calculated scores based on whether the player is the dealer and
   * the type of win.
   *
   * @return an ArrayList of Integer containing the relevant scores
   */
  public ArrayList<Integer> getScores() {
    ArrayList<Integer> scores = new ArrayList<Integer>();
    if (isOya) {
      if (isTsumo) {
        scores.add(this.scoreParentTsumoAll);
      } else {
        scores.add(this.scoreParentRon);
      }
    } else {
      if (isTsumo) {
        scores.add(this.scoreChildTsumoParent);
        scores.add(this.scoreChildTsumoChild);
      } else {
        scores.add(this.scoreChildRon);
      }
    }
    return scores;
  }

  private boolean isOya;
  private boolean isTsumo;

  private int fan;
  private int fu;

  private int scoreChildRon;
  private int scoreChildTsumoParent;
  private int scoreChildTsumoChild;
  private int scoreParentRon;
  private int scoreParentTsumoAll;
}
