package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.entities.Competition;
import es.upm.miw.apaw.api.entities.Jury;
import es.upm.miw.apaw.api.entities.Photographer;
import es.upm.miw.apaw.api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CompetitionBusinessController {

    public String create(CompetitionDto competitionDto) {
        List<Jury> juryList = new ArrayList<>();
        List<Photographer> photographerIdList = new ArrayList<>();

        for (int i = 0; i < competitionDto.getJuryIdList().size(); i++) {
            String juryId = competitionDto.getJuryIdList().get(i);
            Jury jury = DaoFactory.getFactory().getJuryDao().read(juryId).orElseThrow(() -> new NotFoundException("Jury (" + juryId + ")"));
            juryList.add(jury);
        }

        for (int i = 0; i < competitionDto.getPhotographerIdList().size(); i++) {
            String photographerId = competitionDto.getPhotographerIdList().get(i);
            Photographer photographer = DaoFactory.getFactory().getPhotographerDao().read(photographerId).orElseThrow(() -> new NotFoundException("Photographer (" + photographerId + ")"));
            photographerIdList.add(photographer);
        }

        Competition competition = new Competition(competitionDto.getCategory(), competitionDto.getReference(), competitionDto.getPrice(), photographerIdList, juryList);

        DaoFactory.getFactory().getCompetitionDao().save(competition);
        return competition.getId();
    }
}
