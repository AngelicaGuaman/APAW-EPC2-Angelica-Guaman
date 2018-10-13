package es.upm.miw.apaw.api.apiController;

import es.upm.miw.apaw.api.businessController.CompetitionBusinessController;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.dtos.CompetitionIdReferenceDto;
import es.upm.miw.apaw.api.entities.Category;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

import java.util.List;

public class CompetitionApiController {

    public static final String COMPETITIONS = "/competitions";

    public static final String ID = "/{id}";

    public static final String CATEGORY = "/category";

    public static final String SEARCH = "/search";

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

    public List<CompetitionIdReferenceDto> readAll() {
        return this.competitionBusinessController.readAll();
    }

    public void updateCategory(String competitionId, Category category) {
        this.validate(category, "category");
        this.competitionBusinessController.updateCategory(competitionId, category);
    }

    public List<CompetitionIdReferenceDto> find(String query) {
        this.validate(query, "query param q");
        if (!"price".equals(query.split(":>=")[0])) {
            throw new ArgumentNotValidException("query param q is incorrect, missing 'price:>='");
        }
        return this.competitionBusinessController.findByPriceGreaterThanEqual(Integer.valueOf(query.split(":>=")[1]));
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + "is NULL");
        }
    }
}
