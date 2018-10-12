package es.upm.miw.apaw.entities;

import java.time.LocalDateTime;
import java.util.List;

public class PhotographyCompetition {
    private String id;

    private Category category;

    private String description;

    private LocalDateTime date;

    private int price;

    private List<Photographer> photographerList;

    private List<Jury> juryList;

    public PhotographyCompetition(String id) {
        this.id = id;
    }

    public PhotographyCompetition(String id, Category category, String description, LocalDateTime date, int price, List<Photographer> photographerList, List<Jury> juryList) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.date = date;
        this.price = price;
        this.photographerList = photographerList;
        this.juryList = juryList;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Photographer> getPhotographerList() {
        return photographerList;
    }

    public void setPhotographerList(List<Photographer> photographerList) {
        this.photographerList = photographerList;
    }

    public List<Jury> getJuryList() {
        return juryList;
    }

    public void setJuryList(List<Jury> juryList) {
        this.juryList = juryList;
    }
}
