package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.apiController.PhotographerApiController;
import es.upm.miw.apaw.http.Client;
import es.upm.miw.apaw.http.HttpException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PhotographersIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreatePhotographer() {
        this.createPhotographer();
    }

    private String createPhotographer() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(new PhotographerDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testPhotographerInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePhotographerWithoutPhotographerDto() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePhotographerWithoutPhotographerDtoNick() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(new PhotographerDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdatePhotographer() {
        String id = this.createPhotographer();
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).path(PhotographerApiController.ID)
                .expandPath(id).body(new PhotographerDto("angelica")).put();
        new Client().submit(request);
    }

    @Test
    void testUpdatePhotographerWithoutPhotographerDto() {
        String id = this.createPhotographer();
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).path(PhotographerApiController.ID)
                .expandPath(id).body(null).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testUpdatePhotographerNotFoundException() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).path(PhotographerApiController.ID)
                .expandPath("incorrectPath").body(new PhotographerDto("angelica")).put();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}
