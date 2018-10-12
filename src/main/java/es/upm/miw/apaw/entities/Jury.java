package es.upm.miw.apaw.entities;

public class Jury {
    private String id;

    private String nick;

    private String email;

    public Jury(String id, String nick) {
        this.id = id;
        this.nick = nick;
    }

    public Jury(String id, String nick, String email) {
        this.id = id;
        this.nick = nick;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
