package es.upm.miw.apaw.api.dtos;

import es.upm.miw.apaw.api.entities.Competition;

public class CompetitionIdReferenceDto {

    private String id;

    private String reference;

    public CompetitionIdReferenceDto(Competition competition) {
        this.id = competition.getId();
        this.reference = competition.getReference();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
