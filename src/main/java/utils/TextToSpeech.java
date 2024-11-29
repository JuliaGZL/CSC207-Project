package utils;

import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

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

  private TextToSpeech() {
    // Initialize the Azure Speech Synthesizer
    SpeechConfig config = SpeechConfig.fromSubscription(speechKey, speechRegion);
    synthesizer = new SpeechSynthesizer(config);
  }

  public static TextToSpeech getInstance() {
    if (instance == null) {
      instance = new TextToSpeech();
    }
    return instance;
  }

  public void speak(String text) {
    // Convert text to speech
    synthesizer.SpeakText(text);
  }

  public void close() {
    // Close the synthesizer
    synthesizer.close();
  }
}
