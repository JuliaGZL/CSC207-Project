package dataaccess.discordbot;

public class TestFinalChatBotByBuilder {
  public static void main(String[] args) {

    DefaultBotBuilder factory = new DefaultBotBuilder();
    factory.activateBot();
    System.out.println(factory.getMessage());
  }
}
