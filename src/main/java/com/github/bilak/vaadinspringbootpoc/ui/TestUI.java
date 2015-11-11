package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lvasek on 06/10/15.
 */
@Theme("valo")
@SpringUI(path = "/testui")
@Widgetset(value = "com.github.bilak.vaadinspringbootpoc.widgetset.CustomWidgetset")
public class TestUI extends AutoReloadUI {

    private static final Logger log = LoggerFactory.getLogger(TestUI.class);


    @Autowired
    private SpringViewProvider viewProvider;

    MenuLayout root = new MenuLayout();
    ComponentContainer viewContainer = root.getContentContainer();

    CssLayout menu = new CssLayout();
    CssLayout menuItemsLayout = new CssLayout();

    private Navigator navigator;


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        setContent(root);
        root.setWidth("100%");
        addStyleName(ValoTheme.UI_WITH_MENU);

        navigator = new Navigator(this, viewContainer);
        navigator.addProvider(viewProvider);
        navigator.setErrorView(SecuredView.class);
        navigator.addView(SecuredView.VIEW_NAME, SecuredView.class);


        Button securedView = new Button("Secured View");
        securedView.setHtmlContentAllowed(true);
        securedView.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        securedView.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo(SecuredView.VIEW_NAME);
            }
        });
        menuItemsLayout.addComponent(securedView);
        menu.addComponent(menuItemsLayout);
        root.addMenu(menu);

        Button logout = new Button("Logout");
        logout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                log.debug("Authentication before logout {}", SecurityContextHolder.getContext().getAuthentication());
                Page.getCurrent().open("/logout", null);
            }
        });

        Button securedResource = new Button("Secured Action");
        securedResource.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                log.debug("Authentication before secured {}", SecurityContextHolder.getContext().getAuthentication());
                securedAction();
            }
        });

        root.addComponent(logout);
        root.addComponent(securedResource);

    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    private void securedAction() {
        Notification.show("Authorized action performed", Notification.Type.HUMANIZED_MESSAGE);
    }

}
