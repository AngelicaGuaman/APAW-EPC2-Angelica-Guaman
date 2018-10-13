package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.*;

public class DaoMemoryFactory extends DaoFactory {

    private PhotographerDao photographerDao;

    private JuryDao juryDao;

    private CompetitionDao competitionDao;

    private CameraDao cameraDao;

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

    @Override
    public CameraDao getCameraDao() {
        if (cameraDao == null) {
            cameraDao = new CameraDaoMemory();
        }

        return cameraDao;
    }
}
