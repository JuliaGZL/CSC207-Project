package usecase.api_usecase;

import entity.PlayerStats;
import entity.PlayerStatsBuilder;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.HandResult;

public class FeedbackGenerator {
  public static List<String> getFeedback(String Tiles) {
    List<Tile> tiles = new ArrayList<>();
    String[] tokens = Tiles.split(" ");
    List<Integer> numbers = new ArrayList<>();
    for (String token : tokens) {
      if (Character.isDigit(token.charAt(0))) {
        numbers.add(Integer.parseInt(token));
      } else {
        char suit = token.charAt(0);
        for (int num : numbers) {
          String tile = "_" + num + suit;
          Tile newTile = new Tile(BaseTile.valueOf(tile), false, false, false);
          tiles.add(newTile);
        }
        numbers.clear();
      }
    }
    PlayerStats playerStats = new PlayerStatsBuilder().setHand(tiles).build();
    HandResult handResult = HandResult.getInstance(playerStats);
    return handResult.displayHandResult();
  }
}
