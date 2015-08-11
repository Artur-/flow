/*
 * Copyright 2000-2014 Vaadin Ltd.
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
package com.vaadin.tests.server.component.abstractcomponent;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Locale;

import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.junit.Before;
import org.junit.Test;

import com.vaadin.server.ErrorMessage.ErrorLevel;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.tests.design.DeclarativeTestBase;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;

/**
 * Test cases for reading and writing the properties of AbstractComponent.
 * 
 * @since
 * @author Vaadin Ltd
 */
public class AbstractComponentDeclarativeTest extends DeclarativeTestBase<AbstractComponent> {

    private AbstractComponent component;

    @Before
    public void setUp() {
        Label l = new Label();
        l.setContentMode(ContentMode.HTML);
        component = l;
    }

    @Test
    public void testEmptyDesign() {
        String design = "<v-label>";
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testProperties() {
        String design = "<v-label id=\"testId\" primary-style-name=\"test-style\" " + "caption=\"test-caption\" locale=\"fi_FI\" " + "error=\"<div>test-error</div>\" immediate=\"\"/>";
        component.setId("testId");
        component.setPrimaryStyleName("test-style");
        component.setCaption("test-caption");
        component.setLocale(new Locale("fi", "FI"));
        component.setComponentError(new UserError("<div>test-error</div>", com.vaadin.server.AbstractErrorMessage.ContentMode.HTML, ErrorLevel.ERROR));
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testExternalIcon() {
        String design = "<v-label icon=\"http://example.com/example.gif\"/>";
        component.setIcon(new ExternalResource("http://example.com/example.gif"));
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testThemeIcon() {
        String design = "<v-label icon=\"theme://example.gif\"/>";
        component.setIcon(new ThemeResource("example.gif"));
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testFileResourceIcon() {
        String design = "<v-label icon=\"img/example.gif\"/>";
        component.setIcon(new FileResource(new File("img/example.gif")));
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testWidthAndHeight() {
        String design = "<v-label width=\"70%\" height=\"12px\"/>";
        component.setWidth("70%");
        component.setHeight("12px");
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testSizeFull() {
        String design = "<v-label size-full=\"\"/>";
        component.setSizeFull();
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testSizeAuto() {
        String design = "<v-label size-auto=\"\"/>";
        component.setSizeUndefined();
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testHeightFull() {
        String design = "<v-label height-full=\"\"/ width=\"20px\"/>";
        component.setHeight("100%");
        component.setWidth("20px");
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testHeightAuto() {
        String design = "<v-horizontal-split-panel height-auto=\"\"/ width=\"20px\" >";
        // we need to have default height of 100% -> use split panel
        AbstractComponent component = new HorizontalSplitPanel();
        component.setHeight(null);
        component.setWidth("20px");
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testWidthFull() {
        String design = "<v-button width-full=\"\"/ height=\"20px\">Foo</button>";
        AbstractComponent component = new Button();
        component.setCaptionAsHtml(true);
        component.setCaption("Foo");
        component.setHeight("20px");
        component.setWidth("100%");
        testRead(design, component);
        testWrite(design, component);
    }

    @Test
    public void testWidthAuto() {
        String design = "<v-label height=\"20px\"/ width-auto=\"\"/>";
        component.setCaptionAsHtml(false);
        component.setHeight("20px");
        component.setWidth(null);
        testRead(design, component);
        testWrite(design, component);
    }

    private Element createDesign(String key, String value) {
        Attributes attributes = new Attributes();
        attributes.put(key, value);
        Element node = new Element(Tag.valueOf("v-label"), "", attributes);
        return node;
    }

    private Boolean getExplicitImmediate(AbstractComponent component) {
        try {
            Field immediate = AbstractComponent.class.getDeclaredField("explicitImmediateValue");
            immediate.setAccessible(true);
            return (Boolean) immediate.get(component);
        } catch (Exception e) {
            throw new RuntimeException("Getting the field explicitImmediateValue failed.");
        }
    }
}