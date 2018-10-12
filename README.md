# Arquitectura de un mini API-Rest simulado

> ##### [Máster en Ingeniería Web por la Universidad Politécnica de Madrid (miw-upm)](http://miw.etsisi.upm.es)
> ##### Asignatura: *Arquitectura y Patrones para Aplicaciones*

### Estado del código

[![Build Status](https://travis-ci.org/AngelicaGuaman/APAW-EPC2-Angelica-Guaman.svg?branch=master)](https://travis-ci.org/AngelicaGuaman/APAW-EPC2-Angelica-Guaman)

![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=es.upm.miw:APAW-EPC2-Angelica-Guaman&metric=alert_status)

### Tecnologías necesarias
* Java
* Maven
* GitHub
* Travis-ci
* Sonarcloud

A continuación, se muestra el escenario escogido para el desarrollo de ésta práctica.

## Diseño de entidades
![entities](https://github.com/AngelicaGuaman/APAW-EPC2-Angelica-Guaman/blob/develop/docs/entities/entities.png)


## Arquitectura
![themes-entities-class-diagram](https://github.com/AngelicaGuaman/APAW-EPC2-Angelica-Guaman/blob/develop/docs/architecture/themes-architecture-diagram.png)

### Responsabilidades
#### Dispatcher
* Centraliza las peticiones y hace de repartidor
* Recupera los datos de la petición y los pasa como parámetros de método
* Captura las excepciones y las convierte en errores Http
#### restControllers
* Define el path del recurso
* Valida la entrada
* Traspasa la petición a los controladores de la capa de negocio
#### businessControllers
* Procesa la petición, apoyándose en los DAO’s
* Crea las entidades a partir de los DTO’s
* Gestiona la respuesta a partir de las entidades. Delega en los DTO’s la creación a partir de la entidad
#### daos
* Gestionan la BD
#### entities
* Son las entidades persistentes en la BD

## API
### POST /photographers
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /photographers/{id}
#### Parámetros del cuerpo
- `nick`: String (**requerido**)
- `email`: String
#### Respuesta
- 200 OK
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### POST /juries
#### Parámetros del cuerpo
- `email`: String (**requerido**)
#### Respuesta
- 200 OK
  - `id`: String
- 403 BAD_REQUEST
---
### POST /competitions
#### Parámetros del cuerpo
- `reference`: String (**requerido**)
- `category`: Category
- `price`: Integer
- `photographerId`: String
- `juryId`: String
#### Respuesta
- 200 OK
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /competitions
#### Respuesta
- 200 OK
  - `[ {id:String,reference:String} ]`
---
### PATH /competitions/{id}/category
#### Parámetros del cuerpo
- `category`: String (**requerido**)
#### Respuesta
- 200 OK
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /competitions/search?q=price:>=100
#### Respuesta
- 200 OK
  - `[ {id:String,reference:String} ]`
- 403 BAD_REQUEST
---
### POST /cameras
#### Parámetros del cuerpo
- `digital`: Boolean (**requerido**)
- `description`: String (**requerido**)
#### Respuesta
- 200 OK
- 403 BAD_REQUEST
---
### DELETE /cameras/{id}
#### Respuesta
- 200 OK
---

##### Autor: Angélica Guamán Albarracín
