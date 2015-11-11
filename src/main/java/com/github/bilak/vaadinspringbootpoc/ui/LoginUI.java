package com.github.bilak.vaadinspringbootpoc.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.ErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;


/**
 * Created by lvasek on 09/11/15.
 */
@Theme("valo")
@SpringUI(path = "/login")
@Title("Login")
public class LoginUI extends UI implements Button.ClickListener {

    private static final long serialVersionUID = -8667709390052949671L;

    private static final Logger log = LoggerFactory.getLogger(LoginUI.class);

    private VerticalLayout layout;
    private TextField username;
    private PasswordField password;
    private Button btnLogin;
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginUI(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostConstruct
    public void initLayout() {
        layout = new VerticalLayout();
        layout.setSizeFull();

        FormLayout loginLayout = new FormLayout();
        loginLayout.setSizeUndefined();
        loginLayout.setSpacing(true);

        Label label = new Label("user/user </br> admin/admin", ContentMode.HTML);

        username = new TextField("Username");
        username.setId("login-username");
        password = new PasswordField("Password");
        password.setId("login-password");

        btnLogin = new Button("Login");
        btnLogin.addClickListener(this);
        btnLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        loginLayout.addComponent(label);
        loginLayout.addComponent(username);
        loginLayout.addComponent(password);
        loginLayout.addComponent(btnLogin);

        layout.addComponent(loginLayout);
        layout.setComponentAlignment(loginLayout, Alignment.MIDDLE_CENTER);


        setErrorHandler(new ErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                log.error("Unexpected errror", event.getThrowable());
            }
        });
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(layout);

    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            getPage().setLocation("/testui");
            log.debug("Context {}", SecurityContextHolder.getContext().getAuthentication());
        } catch (AuthenticationException e) {
            Notification.show("Login failed.", e.getMessage(), Notification.Type.ERROR_MESSAGE);
        } catch (Exception e) {
            log.error("Unexpected error while logging in", e);
        }
    }


}