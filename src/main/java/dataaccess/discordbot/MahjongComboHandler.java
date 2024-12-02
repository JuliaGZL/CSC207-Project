/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
 * Handles Mahjong combo messages in a Discord bot.
 */
public class MahjongComboHandler extends MessageHandler {
  static final List<Character> LEGAL_IDENTIFIER = Arrays.asList(
      'm', 'p', 's', 'z', 'M', 'P', 'S', 'Z');

  /**
   * Constructs a MahjongComboHandler.
   *
   * @param client  the Discord client
   * @param gateway the GatewayDiscordClient
   */
  public MahjongComboHandler(DiscordClient client, GatewayDiscordClient gateway) {
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
   * Generates a new message based on the content.
   *
   * @param content the content of the message
   * @return the new message
   */
  public static String newMessage(String content) {
    Map<Character, List<Integer>> mahjongCombo = processMahjongInput(content);
    return getResultString(content);
  }

  /**
   * Processes the Mahjong input content.
   *
   * @param content the content of the message
   * @return a map of characters to lists of integers
   */
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
      } else {
        throw new IllegalArgumentException("Integers before legal identifiers must be 1-9");
      }
    }
    scanner.close();
    return charToListMap;
  }

  /**
   * Checks if the message should invoke a response.
   *
   * @param content the content of the message
   * @return true if the message should invoke a response, false otherwise
   */
  public static boolean invokeMessage(String content) {
    return content.split(" ")[0].equalsIgnoreCase("Mahjong");
  }

  /**
   * Checks if the target string is a legal integer.
   *
   * @param target the target string
   * @return true if the target is a legal integer, false otherwise
   */
  public static boolean isLegalInt(String target) {
    char c = target.charAt(0);
    return Character.isDigit(c) && target.length() == 1;
  }

  /**
   * Checks if the target string is a legal identifier.
   *
   * @param target the target string
   * @return true if the target is a legal identifier, false otherwise
   */
  public static boolean isLegalIdentifier(String target) {
    char c = target.charAt(0);
    return Character.isLetter(c) && target.length() == 1;
  }

  /**
   * Generates the result string based on the content.
   *
   * @param content the content of the message
   * @return the result string
   */
  public static String getResultString(String content) {
    content = content.substring(content.indexOf(' ') + 1);
    FeedbackGenerator generator = new FeedbackGenerator();
    List<String> feedback = FeedbackGenerator.getResultString(content);
    return String.join("\n", feedback);
  }
}
