package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

/**
 * Created by lvasek on 10/11/15.
 */
@Theme("valo")
@SpringUI(path = "/logout")
@Title("Login")
public class LogoutUI extends UI {

    @Override
    protected void init(VaadinRequest request) {

    }
}
