package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.PhotographerDao;

public class DaoMemoryFactory extends DaoFactory {

    private PhotographerDao photographerDao;


    @Override
    public PhotographerDao getPhotographerDao() {
        if(photographerDao == null){
            photographerDao = new PhotographerDaoMemory();
        }
        return photographerDao;
    }
}
