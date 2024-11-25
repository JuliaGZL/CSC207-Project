package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.checkerframework.common.returnsreceiver.qual.This;

/**
 * Handles the localization of given enum values to their corresponding string representations. It
 * reads from a localization file to map each element to its localized name.
 *
 * @param <T> the type of keys used for localization
 */
public class Localization<T> {

  private Map<T, String> localization = new HashMap<>();

  private Function<String, T> keyConverter;

  private String resourcePath = This.class.getResource("").getPath();

  /**
   * Constructs a Localization instance with a key converter function.
   *
   * @param keyConverter a function to convert strings to keys of type T
   */
  public Localization(Function<String, T> keyConverter) {
    this.keyConverter = keyConverter;
  }

  /**
   * Initializes the translator by reading the localization file.
   *
   * @param pathToDir the path to the directory containing the localization file
   */
  public void initializeTranslator(String pathToDir) {
    try (BufferedReader br = new BufferedReader(new FileReader(resourcePath + pathToDir))) {
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

          T convertedKey = keyConverter.apply(key);
          localization.put(convertedKey, value);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the localized string representation of the given key.
   *
   * @param key the key to be localized
   * @return the localized string of the key
   * @throws RuntimeException if the key is not found in the localization map
   */
  public String toString(T key) {
    if (!this.localization.containsKey(key)) {
      throw new RuntimeException("Invalid key: " + key);
    }
    return this.localization.get(key);
  }

  /**
   * Converts a list of keys to their localized string representations.
   *
   * @param keys the list of keys to be localized
   * @return a concatenated string of localized keys
   */
  public String keysToString(ArrayList<T> keys) {
    StringBuilder sb = new StringBuilder("|");
    for (T key : keys) {
      sb.append(toString(key)).append("|");
    }
    return sb.toString();
  }

}
