package mahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SelfTest {
  public static void main(String[] args) {
    System.out.println("Hello, Mahjong!");
    List<Tile> tiles = new ArrayList<>();
    System.out.println("Test case: 112233m 123s 123p 11z");
    // test case: 112233m 123s 123p 11z
    tiles.add(new Tile(BaseTile._1m, false, false, false));
    tiles.add(new Tile(BaseTile._1m, false, false, false));
    tiles.add(new Tile(BaseTile._2m, false, false, false));
    tiles.add(new Tile(BaseTile._2m, false, false, false));
    tiles.add(new Tile(BaseTile._3m, false, false, false));
    tiles.add(new Tile(BaseTile._3m, false, false, false));
    tiles.add(new Tile(BaseTile._1s, false, false, false));
    tiles.add(new Tile(BaseTile._2s, false, false, false));
    tiles.add(new Tile(BaseTile._3s, false, false, false));
    tiles.add(new Tile(BaseTile._1p, false, false, false));
    tiles.add(new Tile(BaseTile._2p, false, false, false));
    tiles.add(new Tile(BaseTile._3p, false, false, false));
    tiles.add(new Tile(BaseTile._1z, false, false, false));
    tiles.add(new Tile(BaseTile._1z, false, false, false));
    // System.out.println("Test case: 111222333444m 11z");
    // test case: 111222333444m 11z
    // tiles.add(new Tile(BaseTile._1m, false, false, false));
    // tiles.add(new Tile(BaseTile._1m, false, false, false));
    // tiles.add(new Tile(BaseTile._1m, false, false, false));
    // tiles.add(new Tile(BaseTile._2m, false, false, false));
    // tiles.add(new Tile(BaseTile._2m, false, false, false));
    // tiles.add(new Tile(BaseTile._2m, false, false, false));
    // tiles.add(new Tile(BaseTile._3m, false, false, false));
    // tiles.add(new Tile(BaseTile._3m, false, false, false));
    // tiles.add(new Tile(BaseTile._3m, false, false, false));
    // tiles.add(new Tile(BaseTile._4m, false, false, false));
    // tiles.add(new Tile(BaseTile._4m, false, false, false));
    // tiles.add(new Tile(BaseTile._4m, false, false, false));
    // tiles.add(new Tile(BaseTile._1z, false, false, false));
    // tiles.add(new Tile(BaseTile._1z, false, false, false));
    PlayerStats inst = new PlayerStatsBuilder().build(tiles);
    List<String> ret = HandResult.computeHandResult(inst);
    System.out.println(ret);
  }
}
