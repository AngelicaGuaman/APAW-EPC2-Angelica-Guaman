package es.upm.miw.apaw.entities;

public class Camera {
    private String id;

    private String description;

    private boolean digital;

    public Camera(String id) {
        this.id = id;
    }

    public Camera(String id, String description, boolean digital) {
        this.id = id;
        this.description = description;
        this.digital = digital;
    }

    public String getId() {
        return id;
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
