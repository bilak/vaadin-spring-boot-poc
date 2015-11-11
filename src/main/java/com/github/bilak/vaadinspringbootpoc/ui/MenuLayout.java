package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Created by lvasek on 26/10/15.
 */
public class MenuLayout extends HorizontalLayout {


    CssLayout contentArea = new CssLayout();

    CssLayout menuArea = new CssLayout();

    public MenuLayout() {
        setSizeFull();
        menuArea.setPrimaryStyleName("valo-menu");
        contentArea.setPrimaryStyleName("valo-content");
        contentArea.addStyleName("v-scrollable");
        contentArea.setSizeFull();

        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, 1);
    }

    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    public void addMenu(Component menu) {
        menu.addStyleName("valo-menu-part");
        menuArea.addComponent(menu);
    }
}
