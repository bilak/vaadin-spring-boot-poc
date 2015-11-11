package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by lvasek on 11/11/15.
 */
public abstract class AbstractView  extends VerticalLayout implements View {

    @PostConstruct
    public abstract void init();
}
