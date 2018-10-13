package es.upm.miw.apaw.api.apiController;

import es.upm.miw.apaw.api.businessController.CompetitionBusinessController;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class CompetitionApiController {

    public static final String COMPETITIONS = "/competitions";

    private CompetitionBusinessController competitionBusinessController = new CompetitionBusinessController();

    public String create(CompetitionDto competitionDto) {
        this.validate(competitionDto, "competitionDto");
        this.validate(competitionDto.getReference(), "competitionDto reference");
        this.validate(competitionDto.getJuryIdList(), "competitionDto juryIdList");
        this.validate(competitionDto.getPhotographerIdList(), "competitionDto photographerIdList");
        this.validate(competitionDto.getCategory(), "competitionDto category");
        this.validate(competitionDto.getPrice(), "competitionDto price");
        return this.competitionBusinessController.create(competitionDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + "is NULL");
        }
    }
}
