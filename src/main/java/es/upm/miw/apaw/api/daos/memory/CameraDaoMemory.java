package es.upm.miw.apaw.api.daos.memory;

import es.upm.miw.apaw.api.daos.CameraDao;
import es.upm.miw.apaw.api.entities.Camera;

import java.util.HashMap;

public class CameraDaoMemory extends GenericDaoMemory<Camera> implements CameraDao {

    public CameraDaoMemory() {
        super(new HashMap<>());
    }

    @Override
    public String getId(Camera camera) {
        return camera.getId();
    }

    @Override
    public void setId(Camera camera, String id) {
        camera.setId(id);
    }
}
