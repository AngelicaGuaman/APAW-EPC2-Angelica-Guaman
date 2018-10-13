package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.entities.Photographer;
import es.upm.miw.apaw.api.exceptions.NotFoundException;

public class PhotographerBusinessController {

    public String create(PhotographerDto photographerDto) {
        Photographer photographer = new Photographer(photographerDto.getNick(), null);
        DaoFactory.getFactory().getPhotographerDao().save(photographer);
        return photographer.getId();
    }

    public void updateNick(String id, PhotographerDto photographerDto) {
        Photographer photographer = DaoFactory.getFactory().getPhotographerDao().read(id).orElseThrow(() -> new NotFoundException("Photographer id: " + id));
        photographer.setNick(photographerDto.getNick());
        DaoFactory.getFactory().getPhotographerDao().save(photographer);
    }
}
