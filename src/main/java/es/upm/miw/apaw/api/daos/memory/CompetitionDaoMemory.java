package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.CompetitionDao;
import es.upm.miw.apaw.api.entities.Competition;

import java.util.HashMap;

public class CompetitionDaoMemory extends GenericDaoMemory<Competition> implements CompetitionDao {

    public CompetitionDaoMemory() {
        super(new HashMap<>());
    }


    @Override
    public String getId(Competition competition) {
        return competition.getId();
    }

    @Override
    public void setId(Competition competition, String id) {
        competition.setId(id);
    }
}
