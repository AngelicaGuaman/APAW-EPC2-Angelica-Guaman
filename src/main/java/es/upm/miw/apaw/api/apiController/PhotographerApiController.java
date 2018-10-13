package es.upm.miw.apaw.api.apiController;

import es.upm.miw.apaw.api.businessController.PhotographerBusinessController;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class PhotographerApiController {

    public static final String PHOTOGRAPHERS = "/photogaphers";

    public static final String ID = "/{id}";

    private PhotographerBusinessController photographerBusinessController = new PhotographerBusinessController();

    public String create(PhotographerDto photographerDto) {
        this.validate(photographerDto, "photographerDto");
        this.validate(photographerDto.getNick(), "photographerDto Nick");
        return this.photographerBusinessController.create(photographerDto);
    }

    public void update(String id, PhotographerDto photographerDto) {
        this.validate(photographerDto, "photographerDto");
        this.validate(photographerDto.getNick(), "photographerDto Nick");
        this.photographerBusinessController.updateNick(id, photographerDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + "is NULL");
        }
    }
}
