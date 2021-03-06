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
package com.vaadin.flow.uitest.ui;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.testutil.ChromeBrowserTest;

public class CompositeIT extends ChromeBrowserTest {

    @Test
    public void changeOnClient() {
        open();
        WebElement name = findElement(By.id(CompositeNestedView.NAME_ID));
        WebElement input = findElement(
                By.id(CompositeNestedView.NAME_FIELD_ID));
        Assert.assertEquals("Name on server:", name.getText());
        input.sendKeys("123");
        findElement(By.xpath("//body")).click(); // Make change event happen
        Assert.assertEquals("Name on server: 123", name.getText());

        WebElement serverValueInput = findElement(
                By.id(CompositeView.SERVER_INPUT_ID));
        WebElement serverValueButton = findElement(
                By.id(CompositeView.SERVER_INPUT_BUTTON_ID));

        serverValueInput.sendKeys("server value");
        serverValueButton.click();

        Assert.assertEquals("Name on server: server value", name.getText());

    }
}
