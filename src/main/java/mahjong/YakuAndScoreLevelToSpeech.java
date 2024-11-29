package mahjong;

// MahjongSoul!!!

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.s;

import javazoom.jl.player.Player;
import utils.Constants;

/**
 * Utility class for converting Yaku strings to speech by playing corresponding
 * MP3 files.
 */
public class YakuAndScoreLevelToSpeech {
  // TODO: maybe can change character voice in the future?
  /**
   * The path prefix for the MP3 files.
   */
  private static final String pathPrefix = Constants.resourcePath + "/sounds/yakus/Female-Fuji/";

  /**
   * Plays a series of MP3 files based on the given list of Yaku.
   * Each Yaku in the list corresponds to an MP3 file to be played.
   * 
   * @param yakus the list of Yaku to be played
   */
  public static void playSound(List<Yaku> yakus, ScoreLevel scoreLevel) {
    List<String> tokens = getPlayList(yakus);
    if (scoreLevel != ScoreLevel.None) {
      tokens.add(scoreLevel.toString());
    }
    for (String token : tokens) {
      String filename = token + ".mp3";
      playSoundWithJlayer(pathPrefix + filename);
      try {
        Thread.sleep(100); // Pause for 0.1 second
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  /**
   * Generates a list of tokens representing the Yaku to be played.
   * 
   * @param yakus the list of Yaku
   * @return the list of tokens
   */
  private static List<String> getPlayList(List<Yaku> yakus) {
    List<String> tokens = new ArrayList<>();
    int peidoraCount = 0;
    int akadoraCount = 0;
    int uradoraCount = 0;
    int doraCount = 0;
    int hasSameWindYaku = -1; // 0 for Ton, 1 for Nan, 2 for Shaa, 3 for Pei
    for (Yaku yaku : yakus) {
      switch (yaku) {
        case Peidora:
          peidoraCount += 1;
          break;
        case Akadora:
          akadoraCount += 1;
          break;
        case Uradora:
          uradoraCount += 1;
          break;
        case Dora:
          doraCount += 1;
          break;
        case JikazeTon:
          if (hasSameWindYaku == -1) {
            hasSameWindYaku = 0;
          }
          tokens.add("Ton");
          break;
        case JikazeNan:
          if (hasSameWindYaku == -1) {
            hasSameWindYaku = 1;
          }
          tokens.add("Nan");
          break;
        case JikazeShaa:
          if (hasSameWindYaku == -1) {
            hasSameWindYaku = 2;
          }
          tokens.add("Shaa");
          break;
        case JikazePei:
          if (hasSameWindYaku == -1) {
            hasSameWindYaku = 3;
          }
          tokens.add("Pei");
          break;
        case BakazeTon:
          if (hasSameWindYaku == 0) {
            tokens.add("DoubleTon");
          } else {
            tokens.add("Ton");
          }
          break;
        case BakazeNan:
          if (hasSameWindYaku == 1) {
            tokens.add("DoubleNan");
          } else {
            tokens.add("Nan");
          }
          break;
        case BakazeShaa:
          if (hasSameWindYaku == 2) {
            tokens.add("DoubleShaa");
          } else {
            tokens.add("Shaa");
          }
          break;
        case BakazePei:
          if (hasSameWindYaku == 3) {
            tokens.add("DoublePei");
          } else {
            tokens.add("Pei");
          }
          break;
        default:
          String token = yaku.toString();
          // Special case for Yaku with Naki suffix
          // remove the Naki suffix in token
          if (token.endsWith("Naki")) {
            token = token.substring(0, token.length() - 4);
          }
          tokens.add(token);
          break;
      }
    }
    if (doraCount > 0) {
      tokens.add("Dora" + String.valueOf(doraCount));
    }
    if (peidoraCount > 0) {
      tokens.add("Dora" + String.valueOf(peidoraCount));
    }
    if (akadoraCount > 0) {
      tokens.add("Dora" + String.valueOf(akadoraCount));
    }
    if (uradoraCount > 0) {
      tokens.add("Dora" + String.valueOf(uradoraCount));
    }
    return tokens;
  }

  /**
   * Plays an MP3 file using the JLayer library.
   * 
   * @param filename the name of the MP3 file to be played
   */
  private static void playSoundWithJlayer(String filename) {
    try {
      FileInputStream fis = new FileInputStream(filename);
      BufferedInputStream bis = new BufferedInputStream(fis);
      Player player = new Player(bis);
      player.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
