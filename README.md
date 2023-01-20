## Previo
### Requisitos previos

* Servidor de BD MySQL
* Maven (versión > 3.5.x)
* (opcional) GIT
* (opcional) IDE Java (Eclipse, Netbeans, IntelliJ)

### Crear BD para los ejemplos  (si no se ha hecho antes)

* Crear BD "recetas" en MySQL 

```
mysql -u root -p    [pedirá la contraseña de MySQL]

mysql> create database recetas;
mysql> create user recetas@localhost identified by "recetas";
mysql> grant all privileges on recetas.* to recetas@localhost;

```

Adicionalmente, puede ser necesario establecer un formato de fecha compatible
```
mysql> set @@global.time_zone = '+00:00';
mysql> set @@session.time_zone = '+00:00';
```

## CREAR PROYECTO SPRING BOOT
Existen varias alternativas
* Crear un proyecto Maven vacío e incluir las dependencias de los _starters_ de Spring Boot
* Usar Spring Tool Suite ([https://spring.io/tools](https://spring.io/tools)) y crear un nuevo proyecto _String Starter project_
* Crear el proyecto desde Spring Initializr ([https://start.spring.io/](https://start.spring.io/))

### Características del proyecto
```
Project: Maven Project
Language: Java
Spring Boot version: 2.5.6

Proyecto: 
   groupId: es.uvigo.dagss
   artefactId: recetas
   package: es.uvigo.mei.dagss

Packaging: Jar
Java version: 11

Dependencias a incluir:
    Spring Web MVC
    Spring Data JPA
    Spring HATEOAS
    MySQLDriver
```

### Propiedades de conexión con BD

En `src/main/resources/application.properties`

```
nano src/main/resources/application.properties


spring.datasource.url=jdbc:mysql://localhost:3306/recetas
spring.datasource.username=recetas
spring.datasource.password=recetas
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.show_sql=true

```
