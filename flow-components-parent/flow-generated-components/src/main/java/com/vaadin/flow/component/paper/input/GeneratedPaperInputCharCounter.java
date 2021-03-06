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
package com.vaadin.flow.component.paper.input;

import javax.annotation.Generated;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentSupplier;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.JsonSerializable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;

import elemental.json.JsonObject;

@Generated({ "Generator: com.vaadin.generator.ComponentGenerator#1.0-SNAPSHOT",
        "WebComponent: paper-input-char-counter#2.0.2", "Flow#1.0-SNAPSHOT" })
@Tag("paper-input-char-counter")
@HtmlImport("frontend://bower_components/paper-input/paper-input-char-counter.html")
public class GeneratedPaperInputCharCounter<R extends GeneratedPaperInputCharCounter<R>>
        extends Component implements HasStyle, ComponentSupplier<R> {

    /**
     * <p>
     * Description copied from corresponding location in WebComponent:
     * </p>
     * <p>
     * This overrides the update function in PaperInputAddonBehavior.
     * </p>
     * 
     * @see UpdateState
     * @param state
     *            inputElement: The input element. value: The input value.
     *            invalid: True if the input value is invalid.
     */
    public void update(UpdateState state) {
        getElement().callFunction("update", state.toJson());
    }

    /**
     * Class that encapsulates the data to be sent to the
     * {@link GeneratedPaperInputCharCounter#update(UpdateState)} method.
     */
    public static class UpdateState implements JsonSerializable {
        private JsonObject internalObject;

        public JsonObject getInputElement() {
            return internalObject.getObject("inputElement");
        }

        public void setInputElement(JsonObject inputElement) {
            this.internalObject.put("inputElement", inputElement);
        }

        public String getValue() {
            return internalObject.getString("value");
        }

        public void setValue(String value) {
            this.internalObject.put("value", value);
        }

        public boolean isInvalid() {
            return internalObject.getBoolean("invalid");
        }

        public void setInvalid(boolean invalid) {
            this.internalObject.put("invalid", invalid);
        }

        @Override
        public JsonObject toJson() {
            return internalObject;
        }

        @Override
        public UpdateState readJson(JsonObject value) {
            internalObject = value;
            return this;
        }
    }
}