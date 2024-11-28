package utils;

/**
 * Utility class that holds constant values used throughout the application.
 */
public class Constants {
  /**
   * The path to the resource directory.
   */
  public static final String resourcePath = Constants.class.getClassLoader()
      .getResource("").getPath();
}
