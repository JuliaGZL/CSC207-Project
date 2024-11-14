package mahjong;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles the localization of Yaku enum values to their corresponding string representations. It
 * reads from a localization file to map each Yaku to its localized name.
 */
public class Localization {

  private Map<Yaku, String> yakusLocalization = new HashMap<Yaku, String>();

  /**
   * Initializes the Yaku translator by reading the localization file.
   *
   * @param pathToDir the path to the directory containing the localization file
   */
  public void initializeYakuTranslator(String pathToDir) {
    try (BufferedReader br = new BufferedReader(new FileReader(pathToDir))) {
      String line;
      while ((line = br.readLine()) != null) {

        line = line.trim();


        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }

        if (line.contains(":")) {
          int index = line.indexOf(":");
          String key = line.substring(0, index).trim();
          String value = line.substring(index + 1).trim();

          if ((value.startsWith("\"") && value.endsWith("\""))
              || (value.startsWith("'") && value.endsWith("'"))) {
            value = value.substring(1, value.length() - 1);
          }

          yakusLocalization.put(Yaku.valueOf(key), value);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the localized string representation of the given Yaku.
   *
   * @param yaku the Yaku to be localized
   * @return the localized string of the Yaku
   * @throws RuntimeException if the Yaku is not found in the localization map
   */
  public String toString(Yaku yaku) {
    if (!this.yakusLocalization.containsKey(yaku)) {
      throw new RuntimeException("Invalid Yaku:");
    }
    return this.yakusLocalization.get(yaku);
  }

  /**
   * Converts a list of Yakus to their localized string representations.
   *
   * @param yakus the list of Yakus to be localized
   * @return a concatenated string of localized Yakus
   */
  public String yakusToString(ArrayList<Yaku> yakus) {
    StringBuilder sb = new StringBuilder("|");
    for (Yaku yaku : yakus) {
      sb.append(toString(yaku)).append("|");
    }
    return sb.toString();
  }

}
