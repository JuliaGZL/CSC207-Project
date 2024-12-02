package utils;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
      speechRegion = new String(
          Files.readAllBytes(Paths.get(basePath, "/speech_region.txt"))).trim();
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
   * Converts the given text to speech with the specified rate.
   *
   * @param text the text to be converted to speech
   * @param rate the rate of speech (e.g., 0.4, 0.0, 0.5, 1.0)
   */
  public void speak(String text, float rate) {
    // Construct SSML with the specified rate
    String ssml = "<speak version=\"1.0\" xmlns=\"http://www.w3.org/2001/10/synthesis\" xml:lang=\"en-US\">"
        + "<voice name=\"en-US-JennyNeural\">"
        + "<prosody rate=\"" + rate + "\">"
        + text
        + "</prosody>"
        + "</voice>"
        + "</speak>";
    synthesizer.SpeakSsml(ssml);
  }

  /**
   * Converts the given text to speech in a new thread with the 500% rate.
   *
   * @param text the text to be converted to speech
   */
  public void speakInThread(String text) {
    float rate = 5;
    new Thread(() -> speak(text, rate)).start();
    System.out.println("Speaking: " + text + " at rate: " + rate);
  }

  /**
   * Converts the given text to speech in a new thread with the specified rate.
   *
   * @param text the text to be converted to speech
   * @param rate the rate of speech
   */
  public void speakInThread(String text, float rate) {
    new Thread(() -> speak(text, rate)).start();
    System.out.println("Speaking: " + text + " at rate: " + rate);
  }

  /**
   * Closes the speech synthesizer.
   */
  public void close() {
    // Close the synthesizer
    synthesizer.close();
  }
}
