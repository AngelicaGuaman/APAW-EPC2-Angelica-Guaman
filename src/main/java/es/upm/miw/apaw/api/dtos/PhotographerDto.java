package es.upm.miw.apaw.api.dtos;

public class PhotographerDto {

    private String nick;

    private String email;

    public PhotographerDto(String nick) {
        this.nick = nick;
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

    @Override
    public String toString() {
        return "PhotographerDto{" +
                "nick='" + nick + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
