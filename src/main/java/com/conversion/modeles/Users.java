package com.conversion.modeles;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Users")
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;

    public Users(String email) {
        this.email = email;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
