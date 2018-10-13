package es.upm.miw.apaw.api.http;

import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.apiController.PhotographerApiController;
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

    @Test
    void testPhotographerInvalidRequest() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePhotographerWithoutUserDto() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreatePhotographerWithoutUserDtoNick() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(new PhotographerDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    private String createPhotographer() {
        HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(new PhotographerDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }
}
