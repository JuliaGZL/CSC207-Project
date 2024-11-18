package mahjong;

import java.util.HashMap;
import java.util.Map;

public class BaseTileToPathMapping {
    private static final Map<BaseTiles, String> BASE_TILES_STRING_MAP = new HashMap<>();

    static {
        // Mappings for Man tiles
        BASE_TILES_STRING_MAP.put(BaseTiles._1m, "mahjong-tiles/Man1.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._2m, "mahjong-tiles/Man2.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._3m, "mahjong-tiles/Man3.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._4m, "mahjong-tiles/Man4.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._5m, "mahjong-tiles/Man5.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._6m, "mahjong-tiles/Man6.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._7m, "mahjong-tiles/Man7.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._8m, "mahjong-tiles/Man8.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._9m, "mahjong-tiles/Man9.png");

        // Mappings for Pin tiles
        BASE_TILES_STRING_MAP.put(BaseTiles._1p, "mahjong-tiles/Pin1.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._2p, "mahjong-tiles/Pin2.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._3p, "mahjong-tiles/Pin3.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._4p, "mahjong-tiles/Pin4.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._5p, "mahjong-tiles/Pin5.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._6p, "mahjong-tiles/Pin6.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._7p, "mahjong-tiles/Pin7.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._8p, "mahjong-tiles/Pin8.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._9p, "mahjong-tiles/Pin9.png");

        // Mappings for Sou tiles
        BASE_TILES_STRING_MAP.put(BaseTiles._1s, "mahjong-tiles/Sou1.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._2s, "mahjong-tiles/Sou2.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._3s, "mahjong-tiles/Sou3.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._4s, "mahjong-tiles/Sou4.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._5s, "mahjong-tiles/Sou5.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._6s, "mahjong-tiles/Sou6.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._7s, "mahjong-tiles/Sou7.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._8s, "mahjong-tiles/Sou8.png");
        BASE_TILES_STRING_MAP.put(BaseTiles._9s, "mahjong-tiles/Sou9.png");

        // Mappings for honor tiles
        BASE_TILES_STRING_MAP.put(BaseTiles._1z, "mahjong-tiles/Ton.png");      // East wind
        BASE_TILES_STRING_MAP.put(BaseTiles._2z, "mahjong-tiles/Nan.png");      // South wind
        BASE_TILES_STRING_MAP.put(BaseTiles._3z, "mahjong-tiles/Shaa.png");     // West wind
        BASE_TILES_STRING_MAP.put(BaseTiles._4z, "mahjong-tiles/Pei.png");      // North wind
        BASE_TILES_STRING_MAP.put(BaseTiles._5z, "mahjong-tiles/Haku.png");     // White dragon
        BASE_TILES_STRING_MAP.put(BaseTiles._6z, "mahjong-tiles/Hatsu.png");    // Green dragon
        BASE_TILES_STRING_MAP.put(BaseTiles._7z, "mahjong-tiles/Chun.png");     // Red dragon
    }

    public static String getTilePath(BaseTiles baseTile) {
        return BASE_TILES_STRING_MAP.get(baseTile);
    }
}
