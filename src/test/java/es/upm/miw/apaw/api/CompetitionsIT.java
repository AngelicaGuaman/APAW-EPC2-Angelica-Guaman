package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiController.CompetitionApiController;
import es.upm.miw.apaw.api.apiController.JuryApiController;
import es.upm.miw.apaw.api.apiController.PhotographerApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.entities.Category;
import es.upm.miw.apaw.http.Client;
import es.upm.miw.apaw.http.HttpException;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CompetitionsIT {

    private static final int MAX_ELEMENTS = 3;

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    private List<String> createJuryList() {

        List<String> juryList = new ArrayList<>();

        for (int i = 0; i < MAX_ELEMENTS; i++) {
            HttpRequest request = HttpRequest.builder(JuryApiController.JURIES).body(new JuryDto("jury" + i)).post();
            juryList.add((String) new Client().submit(request).getBody());
        }

        return juryList;
    }

    private List<String> createPhotographerList() {
        List<String> photographerList = new ArrayList<>();

        for (int i = 0; i < MAX_ELEMENTS; i++) {
            HttpRequest request = HttpRequest.builder(PhotographerApiController.PHOTOGRAPHERS).body(new PhotographerDto("photographer" + i)).post();
            photographerList.add((String) new Client().submit(request).getBody());
        }

        return photographerList;
    }

    @Test
    void createCompetition() {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, Category.MACRO, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();
        new Client().submit(request);
    }

    @Test
    void createCompetitionPhotographerIdNotFound() {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = this.createPhotographerList();
        photographerList.add("NoValid");

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, Category.MACRO, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void createCompetitionJuryIdNotFound() {
        List<String> juryList = this.createJuryList();
        juryList.add("NoValid");
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, Category.MACRO, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void createCompetitionWithoutReference() {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto(null, juryList, photographerList, Category.MACRO, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createCompetitionWithoutCategory() {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, null, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createCompetitionWithoutJuryList() {
        List<String> juryList = null;
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, Category.PORTRAIT, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void createCompetitionWithoutPhotographersList() {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = null;

        CompetitionDto competitionDto = new CompetitionDto("SENIOR MACRO", juryList, photographerList, Category.WEDDING, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();

        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
