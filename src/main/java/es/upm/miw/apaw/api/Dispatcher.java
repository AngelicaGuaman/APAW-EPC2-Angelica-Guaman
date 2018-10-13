package es.upm.miw.apaw.api;

import es.upm.miw.apaw.api.dtos.PhotographerDto;
import es.upm.miw.apaw.api.exceptions.ArgumentNotValidException;
import es.upm.miw.apaw.api.exceptions.RequestInvalidException;
import es.upm.miw.apaw.apiController.PhotographerApiController;
import es.upm.miw.apaw.http.HttpRequest;
import es.upm.miw.apaw.http.HttpResponse;
import es.upm.miw.apaw.http.HttpStatus;

public class Dispatcher {

    private PhotographerApiController photographerApiController = new PhotographerApiController();

    public void submit(HttpRequest request, HttpResponse response) {
        String ERROR_MESSAGE = "{'error':'%S'}";
        try {
            switch (request.getMethod()) {
                case POST:
                    this.doPost(request, response);
                    break;
                case GET:
                    throw new RequestInvalidException("method error: " + request.getMethod());
                case PUT:
                    throw new RequestInvalidException("method error: " + request.getMethod());
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
        } catch (Exception exception) {  // Unexpected
            exception.printStackTrace();
            response.setBody(String.format(ERROR_MESSAGE, exception));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void doPost(HttpRequest request, HttpResponse response) {
        if (request.isEqualsPath(PhotographerApiController.PHOTOGRAPHERS)) {
            response.setBody(this.photographerApiController.create((PhotographerDto) request.getBody()));
        } else {
            throw new RequestInvalidException("method error: " + request.getMethod());
        }
    }
}
