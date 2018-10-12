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
![themes-entities-class-diagram](https://github.com/AngelicaGuaman/APAW-EPC2-Angelica-Guaman/blob/develop/docs/architectura/themes-architecture-diagram.png)

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

