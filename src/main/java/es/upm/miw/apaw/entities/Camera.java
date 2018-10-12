package es.upm.miw.apaw.entities;

public class Camera {
    private String id;

    private String reference;

    private boolean digital;

    public Camera(String id) {
        this.id = id;
    }

    public Camera(String id, String reference, boolean digital) {
        this.id = id;
        this.reference = reference;
        this.digital = digital;
    }

    public String getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }
}
