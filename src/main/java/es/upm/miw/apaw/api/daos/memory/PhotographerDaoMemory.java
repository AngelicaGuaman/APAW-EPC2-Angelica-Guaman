package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.PhotographerDao;
import es.upm.miw.apaw.api.entities.Photographer;

import java.util.HashMap;

public class PhotographerDaoMemory extends GenericDaoMemory<Photographer> implements PhotographerDao {

    public PhotographerDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Photographer photographer) {
        return photographer.getId();
    }

    @Override
    public void setId(Photographer photographer, String id) {
        photographer.setId(id);
    }
}
