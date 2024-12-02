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

/**
 * A holder for a message string.
 */
public class MessageHolder {
  static String msg = "";

  /**
   * Sets the message.
   *
   * @param msg the message to set
   */
  static void setMessage(String msg) {
    MessageHolder.msg = msg;
  }

  /**
   * Gets the message without the first word.
   *
   * @return the message without the first word
   */
  static String getMessage() {
    return msg.substring(msg.indexOf(' ') + 1);
  }
}
