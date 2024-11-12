package org.example;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String name;
    private int age;
    private String nickname;
    private String email;

    public User(String userId, String name, int age, String nickname, String email) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "UserId: " + userId + "\nName: " + name + "\nAge: " + age + "\nNickname: " + nickname + "\nEmail: " + email;
    }
}