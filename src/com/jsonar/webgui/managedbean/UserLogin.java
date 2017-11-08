package com.jsonar.webgui.managedbean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;

@ManagedBean(name = "userLogin", eager = true)
@RequestScoped
public class UserLogin {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login() {
        if ((username.equals("Test1") && password.equals("test1@mytest.com"))
                || (username.equals("Test2") && password.equals("test2@mytest.com"))) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("customer-product.xhtml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials"));
        }
    }
}
