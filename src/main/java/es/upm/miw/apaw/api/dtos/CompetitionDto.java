package es.upm.miw.apaw.api.dtos;

import es.upm.miw.apaw.api.entities.Category;

import java.util.List;

public class CompetitionDto {

    private String reference;

    private List<String> juryIdList;

    private List<String> photographerIdList;

    private Category category;

    private Integer price;

    public CompetitionDto(String reference, List<String> juryIdList, List<String> photographerIdList, Category category, Integer price) {
        this.reference = reference;
        this.juryIdList = juryIdList;
        this.photographerIdList = photographerIdList;
        this.category = category;
        this.price = price;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<String> getJuryIdList() {
        return juryIdList;
    }

    public void setJuryIdList(List<String> juryIdList) {
        this.juryIdList = juryIdList;
    }

    public List<String> getPhotographerIdList() {
        return photographerIdList;
    }

    public void setPhotographerIdList(List<String> photographerIdList) {
        this.photographerIdList = photographerIdList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
