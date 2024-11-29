package utils;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Timer;
import java.io.IOException;

/**
 * Utility class for converting text to speech using Azure Cognitive Services.
 */
public class TextToSpeech {

  // This requires files named "speech_key.txt" and "speech_region.txt" in the
  // project root directory
  private static String speechKey;
  private static String speechRegion;

  private SpeechSynthesizer synthesizer;
  private static TextToSpeech instance;

  static {
    try {
      String basePath = Paths.get(Constants.resourcePath).toString();
      speechKey = new String(Files.readAllBytes(Paths.get(basePath, "/speech_key.txt"))).trim();
      speechRegion = new String(Files.readAllBytes(Paths.get(basePath, "/speech_region.txt"))).trim();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Private constructor to initialize the Azure Speech Synthesizer.
   */
  private TextToSpeech() {
    // Initialize the Azure Speech Synthesizer
    SpeechConfig config = SpeechConfig.fromSubscription(speechKey, speechRegion);
    // Note: the voice setting will not overwrite the voice element in input SSML.
    config.setSpeechSynthesisVoiceName("wuu-CN-XiaotongNeural");
    synthesizer = new SpeechSynthesizer(config);
  }

  /**
   * Returns the singleton instance of the TextToSpeech class.
   * 
   * @return the singleton instance of TextToSpeech
   */
  public static TextToSpeech getInstance() {
    if (instance == null) {
      instance = new TextToSpeech();
    }
    return instance;
  }

  /**
   * Converts the given text to speech.
   * 
   * @param text the text to be converted to speech
   */
  public void speak(String text) {
    // Convert text to speech
    synthesizer.SpeakText(text);
  }

  /**
   * Converts the given text to speech in a new thread.
   * 
   * @param text the text to be converted to speech
   */
  public void speakInThread(String text) {
    new Thread(() -> speak(text)).start();
    System.out.println("Speaking: " + text);
  }

  /**
   * Closes the speech synthesizer.
   */
  public void close() {
    // Close the synthesizer
    synthesizer.close();
  }
}
