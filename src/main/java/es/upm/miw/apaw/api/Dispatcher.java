package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.apiController.CompetitionApiController;
import es.upm.miw.apaw.api.apiController.JuryApiController;
import es.upm.miw.apaw.api.dtos.CompetitionDto;
import es.upm.miw.apaw.api.dtos.JuryDto;
import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;
import es.upm.miw.apaw.api.exceptions.NotFoundException;
import es.upm.miw.apaw.api.exceptions.RequestInvalidException;
import es.upm.miw.apaw.api.apiController.PhotographerApiController;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpResponse;
import es.upm.miw.apaw.http.HttpStatus;

public class Dispatcher {

    private PhotographerApiController photographerApiController = new PhotographerApiController();

    private JuryApiController juryApiController = new JuryApiController();

    private CompetitionApiController competitionApiController = new CompetitionApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    this.doGet(request, response);
                    break;
                case PUT:
                    this.doPut(request);
                    break;
                case PATCH:
                    throw new RequestInvalidException("method error: " + request.getMethod());
                case DELETE:
                    throw new RequestInvalidException("method error: " + request.getMethod());
                default: // Unexpected
                    throw new RequestInvalidException("method error: " + request.getMethod());
            }
        } catch (ArgumentNotValidException | RequestInvalidException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.BAD_REQUEST);
        } catch (NotFoundException exception) {
            response.setBody(String.format(ERROR_MESSAGE, exception.getMessage()));
            response.setStatus(HttpStatus.NOT_FOUND);
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PhotographerApiController.PHOTOGRAPHERS)) {
            response.setBody(this.photographerApiController.create((PhotographerDto) request.getBody()));
        } else if (request.isEqualsPath(JuryApiController.JURIES)) {
            response.setBody(this.juryApiController.create((JuryDto) request.getBody()));
        } else if (request.isEqualsPath(CompetitionApiController.COMPETITIONS)) {
            response.setBody(this.competitionApiController.create((CompetitionDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }

    private void doGet(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(CompetitionApiController.COMPETITIONS)) {
            response.setBody(this.competitionApiController.readAll());
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod() + ' ' + request.getPath());
        }
    }

    private void doPut(HttpRequest request) {
        if (request.isEqualsPath(PhotographerApiController.PHOTOGRAPHERS + PhotographerApiController.ID)) {
            this.photographerApiController.update(request.getPath(1), (PhotographerDto) request.getBody());
        } else {
            throw new RequestInvalidException("request error: " + request.getMethod() + ' ' + request.getPath());
        }
    }
}
