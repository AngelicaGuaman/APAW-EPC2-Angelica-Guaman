package es.upm.miw.apaw.api.dtos;

public class CameraDto {

    private String description;

    private boolean digital;

    public CameraDto(String description, boolean digital) {
        this.description = description;
        this.digital = digital;
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
