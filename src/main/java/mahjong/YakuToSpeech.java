package mahjong;

// MahjongSoul!!!

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javazoom.jl.player.Player;
import utils.Constants;

/**
 * Utility class for converting Yaku strings to speech by playing corresponding
 * MP3 files.
 */
public class YakuToSpeech {
  // TODO: maybe can change character voice in the future?
  /**
   * The path prefix for the MP3 files.
   */
  private static final String pathPrefix = Constants.resourcePath + "/sounds/yaku/Female-Fuji";

  /**
   * Plays a series of MP3 files based on the given list of Yaku.
   * Each Yaku in the list corresponds to an MP3 file to be played.
   * 
   * @param yakus the list of Yaku to be played
   */
  public static void playYakuSound(List<Yaku> yakus) {
    List<String> tokens = getPlayList(yakus);
    for (String token : tokens) {
      String filename = token + ".mp3";
      playSoundWithJlayer(pathPrefix + filename);
      try {
        Thread.sleep(250); // Pause for 0.25 second
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
        default:
          tokens.add(yaku.toString());
          break;
      }
    }
    tokens.add("Peidora" + String.valueOf(doraCount));
    tokens.add("Dora" + String.valueOf(peidoraCount));
    tokens.add("Akadora" + String.valueOf(akadoraCount));
    tokens.add("Uradora" + String.valueOf(uradoraCount));
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
