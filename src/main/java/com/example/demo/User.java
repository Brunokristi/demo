package com.example.demo;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String user;
    private String password;
    private String[] invitedTo;
    private String[] created;
    private boolean admin=false;
    private boolean creator=false;

    // Constructor that accepts values for the properties
    public User() {
        this.user = user;
        this.password = password;
        this.invitedTo = invitedTo;
        this.created = created;
        this.admin=admin;
        this.creator=creator;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getInvitedTo() {
        return invitedTo;
    }

    public void setInvitedTo(String[] invitedTo) {
        this.invitedTo = invitedTo;
    }

    public String[] getCreated() {
        return created;
    }

    public void setCreated(String[] created) {
        this.created = created;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean getCreator() {
        return creator;
    }
    public void setCreator(boolean creator) {
        this.creator = creator;
    }

}
