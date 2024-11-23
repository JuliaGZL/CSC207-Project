package usecase.api_usecase;

public class TestFinalChatBotWithFactory {
    public static void main(String[] args) {

        DefaultBotFactory factory = new DefaultBotFactory();
        factory.getBot().run();
    }
}
