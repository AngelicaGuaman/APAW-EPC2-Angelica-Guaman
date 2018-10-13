package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiController.CameraApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.CameraDto;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.http.Client;
import es.upm.miw.apaw.http.HttpException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CamerasIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateCamera() {
        this.createCamera();
    }

    private String createCamera() {
        HttpRequest request = HttpRequest.builder(CameraApiController.CAMERAS).body(new CameraDto("Reflex", true)).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testCameraInvalidRequest() {
        HttpRequest request = HttpRequest.builder(CameraApiController.CAMERAS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateCameraWithoutCameraDto() {
        HttpRequest request = HttpRequest.builder(CameraApiController.CAMERAS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateCameraWithoutCameraDtoDescription() {
        HttpRequest request = HttpRequest.builder(CameraApiController.CAMERAS).body(new CameraDto(null, false)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
