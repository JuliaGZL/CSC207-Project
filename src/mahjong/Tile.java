
package mahjong;

public class Tile {
    public BaseTile tile;
    public boolean redDora;
    public int id;

    public String toString() {
        int number = tile.ordinal() % 9 + 1;
        if (redDora) {
            number = 0;
        }
        switch (tile.ordinal() / 9) {
            case 0:
                return number + "m";
            case 1:
                return number + "p";
            case 2:
                return number + "s";
            case 3:
                return number + "z";
            default:
                throw new RuntimeException("Error Tile object.");
        }
    }

    // ...existing code...
}

enum BaseTile {
    _1m, _2m, _3m, _4m, _5m, _6m, _7m, _8m, _9m,
    _1p, _2p, _3p, _4p, _5p, _6p, _7p, _8p, _9p,
    _1s, _2s, _3s, _4s, _5s, _6s, _7s, _8s, _9s,
    _1z, _2z, _3z, _4z, _5z, _6z, _7z
}

enum Wind {
    East,
    South,
    West,
    North
}

// ...existing code...