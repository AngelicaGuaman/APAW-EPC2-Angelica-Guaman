package es.upm.miw.apaw.api.apiController;

import es.upm.miw.apaw.api.businessController.JuryBusinessController;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class JuryApiController {

    public static final String JURIES = "/juries";

    private JuryBusinessController juryBusinessController = new JuryBusinessController();

    public String create(JuryDto juryDto) {
        this.validate(juryDto, "juryDto");
        this.validate(juryDto.getNick(), "juryDto Nick");
        return this.juryBusinessController.create(juryDto);
    }


    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + "is NULL");
        }
    }
}
