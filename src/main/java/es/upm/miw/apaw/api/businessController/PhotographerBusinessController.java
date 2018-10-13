package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.entities.Photographer;

public class PhotographerBusinessController {

    public String create(PhotographerDto photographerDto) {
        Photographer photographer = new Photographer(photographerDto.getNick(), null);
        DaoFactory.getFactory().getPhotographerDao().save(photographer);
        return photographer.getId();
    }
}
