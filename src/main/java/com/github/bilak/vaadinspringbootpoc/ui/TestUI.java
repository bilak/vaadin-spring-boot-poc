package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lvasek on 06/10/15.
 */
@SpringUI(path = "/testui")
public class TestUI extends UI {

    private static final Logger log = LoggerFactory.getLogger(TestUI.class);

    ObjectProperty op_FirstName = new ObjectProperty("", String.class);
    ObjectProperty op_LastName = new ObjectProperty("", String.class);
    ObjectProperty op_Address = new ObjectProperty("", String.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        PropertysetItem item = new PropertysetItem();
        item.addItemProperty("firstName", op_FirstName);
        item.addItemProperty("lastName", op_LastName);

        final FieldGroup binder = new FieldGroup(item);

        FormLayout form = new FormLayout();

        TextField tf_FirstName = new TextField("First name:", op_FirstName);
        tf_FirstName.setRequired(true);
        tf_FirstName.addValueChangeListener(valueChangeEvent -> log.debug("op_FirstName {}", op_FirstName.getValue()));
        binder.bind(tf_FirstName, "firstName");
        form.addComponent(tf_FirstName);

        TextField tf_LastNAme = new TextField("Last name:", op_LastName);
        tf_LastNAme.setRequired(true);
        tf_LastNAme.addValueChangeListener(valueChangeEvent -> log.debug("op_LastName {}", op_LastName.getValue()));
        binder.bind(tf_LastNAme, "lastName");
        form.addComponent(tf_LastNAme);

        TextField tf_Address = new TextField("Address: ", op_Address);
        tf_Address.setRequired(true);
        tf_Address.addValueChangeListener(valueChangeEvent -> log.debug("op_Address {}", op_Address.getValue()));
        form.addComponent(tf_Address);


        setContent(form);

    }
}
