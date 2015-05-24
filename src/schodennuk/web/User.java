package schodennuk.web;

import java.sql.SQLException;


public abstract class User {
    private int ID;
    private String password;
    private String nickname;
    private String name;
    private String lastname;
    private String section;

    public User(String nickname)
    {
        this.nickname = nickname;
    }


    public abstract boolean getDataFromDB();

    public void setID(int id) {this.ID = id;  }
    public int getID() {return ID; }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }


    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }
}
