package utils;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
  private Voice voice;
  private static TextToSpeech instance;

  private TextToSpeech() {
    // Initialize the voice
    voice = VoiceManager.getInstance().getVoice("kevin16");
    if (voice != null) {
      voice.allocate();
    } else {
      throw new IllegalStateException("Voice 'kevin16' not found.");
    }
  }

  public static TextToSpeech getInstance() {
    if (instance == null) {
      instance = new TextToSpeech();
    }
    return instance;
  }

  public void speak(String text) {
    // Convert text to speech
    voice.speak(text);
  }

  public void close() {
    // Deallocate the voice
    voice.deallocate();
  }
}
