package com.janfranco.springaop.entity;

import java.util.Random;

public class Account {

    private int id;
    private String name;

    public Account(String name) {
        this.id = new Random().nextInt();
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
