package es.upm.miw.apaw.api.entities;

import java.time.LocalDateTime;
import java.util.List;

public class Competition {
    private String id;

    private Category category;

    private String reference;

    private LocalDateTime date;

    private Integer price;

    private List<Photographer> photographerList;

    private List<Jury> juryList;

    public Competition(Category category, String reference, Integer price, List<Photographer> photographerList, List<Jury> juryList) {
        assert reference != null;
        assert juryList != null;
        assert photographerList != null;

        this.category = category;
        this.reference = reference;
        this.date = LocalDateTime.now();
        this.price = price;
        this.photographerList = photographerList;
        this.juryList = juryList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", reference='" + reference + '\'' +
                ", date=" + date +
                ", category=" + category +
                ", price=" + price +
                ", juryList=" + juryList +
                ", photographerList=" + photographerList +
                '}';
    }
}
