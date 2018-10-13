package es.upm.miw.apaw.api.dtos;

public class JuryDto extends PersonDto {

    public JuryDto(String nick) {
        super(nick);
    }

    @Override
    public String toString() {
        return "JuryDto{" +
                "nick='" + this.getNick() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
