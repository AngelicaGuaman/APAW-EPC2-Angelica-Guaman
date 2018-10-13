package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiController.JuryApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.http.Client;
import es.upm.miw.apaw.http.HttpException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuriesIT {

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateJury() {
        this.createJury();
    }

    private String createJury() {
        HttpRequest request = HttpRequest.builder(JuryApiController.JURIES).body(new JuryDto("uno")).post();
        return (String) new Client().submit(request).getBody();
    }

    @Test
    void testJuryInvalidRequest() {
        HttpRequest request = HttpRequest.builder(JuryApiController.JURIES).path("/invalid").body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateJuryWithoutJuryDto() {
        HttpRequest request = HttpRequest.builder(JuryApiController.JURIES).body(null).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testCreateJuryWithoutJuryDtoNick() {
        HttpRequest request = HttpRequest.builder(JuryApiController.JURIES).body(new JuryDto(null)).post();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
