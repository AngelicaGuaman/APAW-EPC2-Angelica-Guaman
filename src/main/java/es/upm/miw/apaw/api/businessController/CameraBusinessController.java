package es.upm.miw.apaw.api.businessController;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.dtos.CameraDto;
import es.upm.miw.apaw.api.entities.Camera;

public class CameraBusinessController {

    public String create(CameraDto cameraDto) {
        Camera camera = new Camera(cameraDto.getDescription(), cameraDto.isDigital());
        DaoFactory.getFactory().getCameraDao().save(camera);
        return camera.getId();
    }
}
