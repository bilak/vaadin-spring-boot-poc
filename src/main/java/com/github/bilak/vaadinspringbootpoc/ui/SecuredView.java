package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by lvasek on 10/11/15.
 */
@Theme("valo")
@SpringView(name = SecuredView.VIEW_NAME)
@Title("Login")
@PreAuthorize("hasAnyRole('ADMIN')")
public class SecuredView extends AbstractView {

    public static final String VIEW_NAME = "secured";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @Override
    public void init() {
        Label label = new Label("Welcome at secured page");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(label);
        addComponent(label);
    }
}
