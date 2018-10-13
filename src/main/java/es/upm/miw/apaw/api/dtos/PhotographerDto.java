package es.upm.miw.apaw.api.dtos;

public class PhotographerDto extends PersonDto {

    public PhotographerDto(String nick) {
        super(nick);
    }

    @Override
    public String toString() {
        return "PhotographerDto{" +
                "nick='" + this.getNick() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                '}';
    }
}
