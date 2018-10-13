package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.dtos.CompetitionIdReferenceDto;
import es.upm.miw.apaw.api.entities.Category;
import es.upm.miw.apaw.api.entities.Competition;
import es.upm.miw.apaw.api.entities.Jury;
import es.upm.miw.apaw.api.entities.Photographer;
import es.upm.miw.apaw.api.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<CompetitionIdReferenceDto> readAll() {
        return DaoFactory.getFactory().getCompetitionDao().findAll().stream().map(competition -> new CompetitionIdReferenceDto(competition)).collect(Collectors.toList());
    }

    public void updateCategory(String competitionId, Category category) {
        Competition competition = DaoFactory.getFactory().getCompetitionDao().read(competitionId)
                .orElseThrow(() -> new NotFoundException("Competition (" + competitionId + ")"));
        competition.setCategory(category);
        DaoFactory.getFactory().getCompetitionDao().save(competition);
    }


    public List<CompetitionIdReferenceDto> findByPriceGreaterThanEqual(Integer value) {
        return DaoFactory.getFactory().getCompetitionDao().findAll().stream()
                .filter(competition -> competition.getPrice() >= value)
                .map(CompetitionIdReferenceDto::new)
                .collect(Collectors.toList());
    }
}
