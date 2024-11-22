package usecase.api_usecase;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import java.util.*;

public class MahjongComboInteractor extends MessageInteractor {
    static final List<Character> LEGAL_IDENTIFIER = Arrays.asList(
            'm', 'p', 's', 'z', 'M', 'P', 'S', 'Z');

    public MahjongComboInteractor(DiscordClient client, GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                return sendMessage(event, newMessage(content, memberName));
            }

            return SubEventCreator.defaultReturn();
        });
    }

    public static String newMessage(String content, String memberName) {
        Map<Character, List<Integer>> mahjongCombo = processMahjongInput(content);
        return getFeedback(mahjongCombo);
    }

    public static Map<Character, List<Integer>> processMahjongInput(String content) {
        Scanner scanner = new Scanner(content);
        String next = scanner.next();
        List<Integer> tempConnectedInts = new ArrayList<>();
        Map<Character, List<Integer>> charToListMap = new HashMap<>();
        while (scanner.hasNext()) {
            next = scanner.next();
            if (isLegalInt(next)) {
                tempConnectedInts.add(Integer.parseInt(next));
            } else if (isLegalIdentifier(next)) {
                Character identifier = Character.toLowerCase(next.charAt(0));
                charToListMap.computeIfAbsent(identifier, k -> new ArrayList<>());
                List<Integer> original = charToListMap.get(identifier);
                original.addAll(tempConnectedInts);
                Collections.sort(original);
                tempConnectedInts = new ArrayList<>();
            }
            else {
                throw new IllegalArgumentException("Integers before legal identifiers must be 1-9");
            }
        }
        scanner.close();
        return charToListMap;
    }

    public static boolean invokeMessage(String content) {
        return content.split(" ")[0].equalsIgnoreCase("Mahjong");
    }

    public static boolean isLegalInt(String target) {
        char c = target.charAt(0);
        return Character.isDigit(c) && target.length() == 1;
    }

    public static boolean isLegalIdentifier(String target) {
        char c = target.charAt(0);
        return Character.isLetter(c) && target.length() == 1;
    }

    public static String getFeedback(Map<Character, List<Integer>> mahjongCombo) {
        return "Unimplemented";
    }
}
