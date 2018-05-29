package cn.mycommons.bifrost.demo.bean;

import java.util.List;

/**
 * User <br/>
 * Created by xiaqiulei on 2018-05-29.
 */
public class User {

    private int id;

    private String name;

    private List<Message> messages;

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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", messages=" + messages +
                '}';
    }
}