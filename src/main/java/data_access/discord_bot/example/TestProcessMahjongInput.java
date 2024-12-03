package data_access.discord_bot.example;

import data_access.discord_bot.MahjongComboHandler;

public class TestProcessMahjongInput {
    public static void main(String[] args) {
        System.out.println((MahjongComboHandler.processMahjongInput(
                "Mahjong 2 1 m 1 2 s 1 2 z 3 m")));
    }
}
