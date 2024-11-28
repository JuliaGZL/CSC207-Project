package utils;

// Ichihime LOVE!

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

/**
 * Utility class for converting Yaku strings to speech by playing corresponding
 * MP3 files.
 */
public class YakuToSpeech {
  private static final String pathPrefix = Constants.resourcePath + "/yakuSounds/";

  /**
   * Plays a series of MP3 files based on the given input string.
   * Each token in the input string corresponds to an MP3 file to be played.
   * 
   * @param input the input string containing tokens separated by spaces
   */
  public static void playYakuSound(String input) {
    String[] tokens = input.split(" ");
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
