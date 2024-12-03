package dataaccess.discordbot;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles Mahjong combo commands in the Discord bot.
 *
 * This handler listens for messages related to Mahjong
 * combinations and processes the input
 * to generate feedback based on the Mahjong hand provided.
 * It handles valid Mahjong input
 * and returns a string representation of the result.
 */
public class MahjongComboHandler extends MessageHandler {

    /**
     * List of legal identifiers for Mahjong tiles: m, p, s, z,
     * and their uppercase versions.
     */
    static final List<Character> LEGAL_IDENTIFIER = Arrays.asList(
            'm', 'p', 's', 'z', 'M', 'P', 'S', 'Z');

    /**
     * Constructs a MahjongComboHandler to process Mahjong combo commands.
     *
     * This constructor sets up the handler to listen for
     * messages containing Mahjong combo data,
     * and it processes the message by invoking `processMahjongInput`
     * to determine the result.
     *
     * @param client the Discord client used to interact with Discord
     * @param gateway the GatewayDiscordClient used to
     *                interact with the Discord gateway
     */
    public MahjongComboHandler(final DiscordClient client,
                               final GatewayDiscordClient gateway) {
        super(client, gateway, MessageCreateEvent.class, event -> {
            String memberName = getMemberName(event);
            String content = getContent(event);

            if (invokeMessage(content)) {
                MessageHolder.setMessage(content);
                return sendMessage(event, newMessage(content));
            }

            return EventHandler.defaultReturn();
        });
    }

    /**
     * Generates a new message based on Mahjong combo processing.
     *
     * @param content the input content from the message
     * @return a string containing the result of the Mahjong combo analysis
     */
    public static String newMessage(final String content) {
        Map<Character, List<Integer>>
                mahjongCombo = processMahjongInput(content);
        return getResultString(content);
    }

    /**
     * Processes the Mahjong combo input to group and sort the
     * integers based on their identifier.
     *
     * This method parses the content string and separates the
     * numbers (1-9) and identifiers
     * (m, p, s, z). It then organizes the numbers into a map
     * with the identifier as the key.
     *
     * @param content the input content representing a Mahjong hand
     * @return a map from Mahjong tile identifiers to a sorted list of integers
     * @throws IllegalArgumentException if the input contains
     * invalid integer values or identifiers
     */
    public static Map<Character, List<Integer>>
                        processMahjongInput(final String content) {
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
                charToListMap.computeIfAbsent(
                        identifier, k -> new ArrayList<>());
                List<Integer> original = charToListMap.get(identifier);
                original.addAll(tempConnectedInts);
                Collections.sort(original);
                tempConnectedInts = new ArrayList<>();
            }
            else {
                throw new IllegalArgumentException(
                        "Integers before legal identifiers must be 1-9");
            }
        }
        scanner.close();
        return charToListMap;
    }

    /**
     * Determines if the message content corresponds to a Mahjong combo command.
     *
     * This method checks if the content starts with
     * "Mahjong" (case-insensitive).
     *
     * @param content the input content from the message
     * @return true if the content starts with "Mahjong", false otherwise
     */
    public static boolean invokeMessage(final String content) {
        return content.split(" ")[0].equalsIgnoreCase("Mahjong");
    }

    /**
     * Checks if the input string represents a legal integer (1-9).
     *
     * @param target the string to check
     * @return true if the string is a single digit between
     * 1 and 9, false otherwise
     */
    public static boolean isLegalInt(final String target) {
        char c = target.charAt(0);
        return Character.isDigit(c) && target.length() == 1;
    }

    /**
     * Checks if the input string represents a legal
     * Mahjong tile identifier (m, p, s, z).
     *
     * @param target the string to check
     * @return true if the string is a single letter (m, p, s, z),
     * false otherwise
     */
    public static boolean isLegalIdentifier(final String target) {
        char c = target.charAt(0);
        return Character.isLetter(c) && target.length() == 1;
    }

    /**
     * Generates a result string based on the Mahjong hand input.
     *
     * This method processes the input and returns the
     * feedback from the `FeedbackGenerator`.
     *
     * @param content the input content to analyze
     * @return a string containing feedback from the Mahjong analysis
     */
    public static String getResultString(String content) {
        content = content.substring(content.indexOf(' ') + 1);
        FeedbackGenerator generator = new FeedbackGenerator();
        List<String> feedback =  FeedbackGenerator.getResultString(content);
        return String.join("\n", feedback);
    }
}
