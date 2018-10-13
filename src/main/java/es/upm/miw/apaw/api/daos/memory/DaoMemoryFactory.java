package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.CompetitionDao;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.JuryDao;
import es.upm.miw.apaw.api.daos.PhotographerDao;

public class DaoMemoryFactory extends DaoFactory {

    private PhotographerDao photographerDao;

    private JuryDao juryDao;

    private CompetitionDao competitionDao;

    @Override
    public PhotographerDao getPhotographerDao() {
        if (photographerDao == null) {
            photographerDao = new PhotographerDaoMemory();
        }
        return photographerDao;
    }

    @Override
    public JuryDao getJuryDao() {
        if (juryDao == null) {
            juryDao = new JuryDaoMemory();
        }

        return juryDao;
    }

    @Override
    public CompetitionDao getCompetitionDao() {
        if (competitionDao == null) {
            competitionDao = new CompetitionDaoMemory();
        }

        return competitionDao;
    }


}
