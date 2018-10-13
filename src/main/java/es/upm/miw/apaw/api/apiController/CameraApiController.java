package es.upm.miw.apaw.api.apiController;

import es.upm.miw.apaw.api.businessController.CameraBusinessController;
import es.upm.miw.apaw.api.dtos.CameraDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;

public class CameraApiController {

    public static final String CAMERAS = "/cameras";

    private CameraBusinessController cameraBusinessController = new CameraBusinessController();

    public String create(CameraDto cameraDto) {
        this.validate(cameraDto, "cameraDto");
        this.validate(cameraDto.getDescription(), "cameraDto description");
        this.validate(cameraDto.isDigital(), "cameraDto digital");
        return this.cameraBusinessController.create(cameraDto);
    }

    private void validate(Object property, String message) {
        if (property == null) {
            throw new ArgumentNotValidException(message + "is NULL");
        }
    }
}
