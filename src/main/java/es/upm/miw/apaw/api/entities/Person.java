package es.upm.miw.apaw.api.entities;

public class Person {
    private String id;

    private String nick;

    private String email;

    public Person(String id, String nick) {
        this.id = id;
        this.nick = nick;
        this.email = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
