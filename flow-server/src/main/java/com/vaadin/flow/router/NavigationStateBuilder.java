/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.router;

import com.vaadin.ui.Component;

/**
 * A builder class for constructing new {@link NavigationState} instances.
 *
 * @author Vaadin Ltd.
 */
public class NavigationStateBuilder {

    private NavigationState currentState;

    /**
     * Constructs a new NavigationStateBuilder.
     */
    public NavigationStateBuilder() {
        currentState = new NavigationState();
    }

    /**
     * Assigns the given navigation target to the navigation state being built.
     *
     * @param navigationTarget
     *            the navigation target
     * @return this builder, for chaining
     */
    public NavigationStateBuilder withTarget(
            Class<? extends Component> navigationTarget) {
        currentState.setNavigationTarget(navigationTarget);
        return this;
    }

    /**
     * Returns the NavigationState instance that has been built so far and
     * resets the internal state of this builder.
     *
     * @return the built NavigationState instance
     */
    public NavigationState build() {
        NavigationState toReturn = currentState;
        currentState = new NavigationState();
        return toReturn;
    }
}
