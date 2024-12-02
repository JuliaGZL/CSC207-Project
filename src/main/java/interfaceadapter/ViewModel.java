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

package interfaceadapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The ViewModel for our CA implementation.
 * This class delegates work to a PropertyChangeSupport object for
 * managing the property change events.
 *
 * @param <T> The type of state object contained in the model.
 */
public class ViewModel<T> {

  private final String viewName;

  private final PropertyChangeSupport support = new PropertyChangeSupport(this);

  private T state;

  /**
   * Constructs a ViewModel with the specified view name.
   *
   * @param viewName the name of the view
   */
  public ViewModel(String viewName) {
    this.viewName = viewName;
  }

  /**
   * gets the name of this ViewModel.
   *
   * @return The name of this ViewModel
   */
  public String getViewName() {
    return this.viewName;
  }

  /**
   * gets the state of this ViewModel.
   *
   * @return The state of this ViewModel
   */
  public T getState() {
    return this.state;
  }

  /**
   * Sets the state of this ViewModel.
   *
   * @param state The new state to be set
   */
  public void setState(T state) {
    this.state = state;
  }

  /**
   * Fires a property changed event for the state of this ViewModel.
   */
  public void firePropertyChanged() {
    this.support.firePropertyChange("state", null, this.state);
  }

  /**
   * Fires a property changed event for the state of this ViewModel, which
   * allows the user to specify a different propertyName. This can be useful
   * when a class is listening for multiple kinds of property changes.
   * <p/>
   * For example, the LoggedInView listens for two kinds of property changes;
   * it can use the property name to distinguish which property has changed.
   *
   * @param propertyName the label for the property that was changed
   */
  public void firePropertyChanged(String propertyName) {
    this.support.firePropertyChange(propertyName, null, this.state);
  }

  /**
   * Adds a PropertyChangeListener to this ViewModel.
   *
   * @param listener The PropertyChangeListener to be added
   */
  public void addPropertyChangeListener(PropertyChangeListener listener) {
    this.support.addPropertyChangeListener(listener);
  }
}
