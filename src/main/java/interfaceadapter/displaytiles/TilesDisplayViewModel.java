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

package interfaceadapter.displaytiles;

import interfaceadapter.ViewModel;

/**
 * ViewModel for displaying tiles.
 */
public class TilesDisplayViewModel extends ViewModel<TilesDisplayState> {

  /**
   * Constructs a TilesDisplayViewModel with the specified view name.
   *
   * @param viewName the name of the view
   */
  public TilesDisplayViewModel(String viewName) {
    super(viewName);
    setState(new TilesDisplayState());
  }
}
