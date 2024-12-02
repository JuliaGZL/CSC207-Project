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

package interfaceadapter.editstatus;

import interfaceadapter.ViewModel;

/**
 * The ViewModel for the Edit Status View.
 */
public class EditStatusViewModel extends ViewModel<EditStatusState> {

  /**
   * The types of tiles available.
   */
  public static final String[] TILE_TYPES = { "Hand", "Dora", "Uradora" };

  /**
   * The types of wins available.
   */
  public static final String[] WIN_TYPES = { "Tsumo", "Ron" };

  /**
   * The types of winds available.
   */
  public static final String[] WINDS = { "Ton", "Nan", "Shaa", "Pei" };

  /**
   * The attributes available.
   */
  public static final String[] ATTRIBUTES = {
      "Riichi", "Double Riichi", "One-shot", "Under the Sea",
      "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou" };

  /**
   * Index for the Riichi attribute.
   */
  public static final int RIICHI_INDEX = 0;

  /**
   * Index for the Double Riichi attribute.
   */
  public static final int DOUBLE_RIICHI_INDEX = 1;

  /**
   * Index for the One-shot attribute.
   */
  public static final int ONE_SHOT_INDEX = 2;

  /**
   * Index for the Under the Sea attribute.
   */
  public static final int UNDER_THE_SEA_INDEX = 3;

  /**
   * Index for the Under the River attribute.
   */
  public static final int UNDER_THE_RIVER_INDEX = 4;

  /**
   * Index for the After a Kan attribute.
   */
  public static final int AFTER_A_KAN_INDEX = 5;

  /**
   * Index for the Robbing a Kan attribute.
   */
  public static final int ROBBING_A_KAN_INDEX = 6;

  /**
   * Index for the Tenhou attribute.
   */
  public static final int TENHOU_INDEX = 7;

  /**
   * Index for the Chiihou attribute.
   */
  public static final int CHIIHOU_INDEX = 8;

  /**
   * Constructs a new EditStatusViewModel.
   */
  public EditStatusViewModel() {
    super("edit status");
    setState(new EditStatusState());
  }
}
