package es.upm.miw.apaw.api.entities;

public class Camera {
    private String id;

    private String description;

    private boolean digital;

    public Camera(String description, boolean digital) {
        this.description = description;
        this.digital = digital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }
}
