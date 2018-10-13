package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiController.CompetitionApiController;
import es.upm.miw.apaw.api.apiController.JuryApiController;
import es.upm.miw.apaw.api.apiController.PhotographerApiController;
import es.upm.miw.apaw.api.daos.DaoFactory;
import es.upm.miw.apaw.api.daos.memory.DaoMemoryFactory;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.dtos.CompetitionIdReferenceDto;
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

import static org.junit.jupiter.api.Assertions.*;

public class CompetitionsIT {

    private static final int MAX_ELEMENTS = 3;

    @BeforeAll
    static void before() {
        DaoFactory.setFactory(new DaoMemoryFactory());
    }

    @Test
    void testCreateCompetition() {
        this.createCompetition("SENIOR MACRO");
    }

    private String createCompetition(String reference) {
        List<String> juryList = this.createJuryList();
        List<String> photographerList = this.createPhotographerList();

        CompetitionDto competitionDto = new CompetitionDto(reference, juryList, photographerList, Category.MACRO, 100);

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).body(competitionDto).post();
        return (String) new Client().submit(request).getBody();
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
    void testReadAll() {
        for (int i = 0; i < MAX_ELEMENTS; i++) {
            this.createCompetition("competition" + i);
        }

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).get();
        List<CompetitionIdReferenceDto> competitions = (List<CompetitionIdReferenceDto>) new Client().submit(request).getBody();
        assertTrue(competitions.size() >= MAX_ELEMENTS);
    }


    @Test
    void testUpdateCategory() {
        String id = this.createCompetition("MACRO");
        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).path(CompetitionApiController.ID)
                .expandPath(id).path(CompetitionApiController.CATEGORY).body(Category.WEDDING).patch();
        new Client().submit(request);
    }

    @Test
    void testSearchPriceCompetitionIsNotEmpty() {
        this.createCompetition("uno");

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).path(CompetitionApiController.SEARCH)
                .param("q", "price:>=100").get();
        List<CompetitionIdReferenceDto> themes = (List<CompetitionIdReferenceDto>) new Client().submit(request).getBody();
        assertFalse(themes.isEmpty());
    }

    @Test
    void testSearchPriceCompetitionIsEmpty() {
        this.createCompetition("uno");

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).path(CompetitionApiController.SEARCH)
                .param("q", "price:>=800").get();
        List<CompetitionIdReferenceDto> themes = (List<CompetitionIdReferenceDto>) new Client().submit(request).getBody();
        assertTrue(themes.isEmpty());
    }

    @Test
    void testSearchPriceWithoutParamQ() {
        this.createCompetition("uno");

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).path(CompetitionApiController.SEARCH)
                .param("error", "price:>=80").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void testSearchPriceParamError() {
        this.createCompetition("uno");

        HttpRequest request = HttpRequest.builder(CompetitionApiController.COMPETITIONS).path(CompetitionApiController.SEARCH)
                .param("error", "error:>=80").get();
        HttpException exception = assertThrows(HttpException.class, () -> new Client().submit(request));
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
