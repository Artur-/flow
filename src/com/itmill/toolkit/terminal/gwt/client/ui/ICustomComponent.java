/* 
@ITMillApache2LicenseForJavaFiles@
 */

package com.itmill.toolkit.terminal.gwt.client.ui;

import java.util.Set;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.itmill.toolkit.terminal.gwt.client.ApplicationConnection;
import com.itmill.toolkit.terminal.gwt.client.Container;
import com.itmill.toolkit.terminal.gwt.client.Paintable;
import com.itmill.toolkit.terminal.gwt.client.RenderSpace;
import com.itmill.toolkit.terminal.gwt.client.UIDL;
import com.itmill.toolkit.terminal.gwt.client.Util;

public class ICustomComponent extends SimplePanel implements Container {

    private static final String CLASSNAME = "i-customcomponent";
    private String height;
    private ApplicationConnection client;
    private boolean rendering;
    private String width;
    private RenderSpace renderSpace = new RenderSpace();

    public ICustomComponent() {
        super();
        setStyleName(CLASSNAME);
    }

    public void updateFromUIDL(UIDL uidl, final ApplicationConnection client) {
        rendering = true;
        if (client.updateComponent(this, uidl, true)) {
            rendering = false;
            return;
        }
        this.client = client;

        final UIDL child = uidl.getChildUIDL(0);
        if (child != null) {
            final Paintable p = client.getPaintable(child);
            if (p != getWidget()) {
                if (getWidget() != null) {
                    client.unregisterPaintable((Paintable) getWidget());
                    clear();
                }
                setWidget((Widget) p);
            }
            p.updateFromUIDL(child, client);
        }

        boolean updateDynamicSize = updateDynamicSize();
        if (updateDynamicSize && width != null && width.contains("%")) {
            DeferredCommand.addCommand(new Command() {
                public void execute() {
                    // FIXME deferred relative size update needed to fix some
                    // scrollbar issues in sampler. This must be the wrong way
                    // to do it. Might be that some other component is broken.
                    client.handleComponentRelativeSize(ICustomComponent.this);

                }
            });
        }

        renderSpace.setWidth(getElement().getOffsetWidth());
        renderSpace.setHeight(getElement().getOffsetHeight());

        rendering = false;
    }

    private boolean updateDynamicSize() {
        boolean updated = false;
        if (isDynamicWidth()) {
            int childWidth = Util.getRequiredWidth(getWidget());
            getElement().getStyle().setPropertyPx("width", childWidth);
            updated = true;
        }
        if (isDynamicHeight()) {
            int childHeight = Util.getRequiredHeight(getWidget());
            getElement().getStyle().setPropertyPx("height", childHeight);
            updated = true;
        }

        return updated;
    }

    private boolean isDynamicWidth() {
        return width == null || width.equals("");
    }

    private boolean isDynamicHeight() {
        return height == null || height.equals("");
    }

    public boolean hasChildComponent(Widget component) {
        if (getWidget() == component) {
            return true;
        } else {
            return false;
        }
    }

    public void replaceChildComponent(Widget oldComponent, Widget newComponent) {
        if (hasChildComponent(oldComponent)) {
            clear();
            setWidget(newComponent);
        } else {
            throw new IllegalStateException();
        }
    }

    public void updateCaption(Paintable component, UIDL uidl) {
        // NOP, custom component dont render composition roots caption
    }

    public boolean requestLayout(Set<Paintable> child) {
        return !updateDynamicSize();
    }

    public RenderSpace getAllocatedSpace(Widget child) {
        return renderSpace;
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        renderSpace.setHeight(getElement().getOffsetHeight());

        if (!height.equals(this.height)) {
            this.height = height;
            if (!rendering) {
                client.runDescendentsLayout(this);
            }
        }
    }

    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        renderSpace.setWidth(getElement().getOffsetWidth());

        if (!width.equals(this.width)) {
            this.width = width;
            if (!rendering) {
                client.runDescendentsLayout(this);
            }
        }
    }

}
