APPLICATION TASKS

1. Wprowadzenie
Aplikacja "Task" pozwala na zarządzanie zadaniami. Możliwości dodawania, usuwania, edycji oraz wyświetlania zadań.
Aplikacja została oparta o architekturę REST (Representational State Transfer) API i korzysta z protokołu HTTP
do wykonywania zapytań i komunikacji z serwerem, tak aby spełniała założenia CRUD (Create, Read, Update, Delete).

2. Technologie

    - Język programowania: Java Version 17;
    - Framework: Spring Boot;
        zależności:
        + Spring Data JPA - biblioteka do pracy z bazą danych (Hibernate), -> implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
        + Spring Boot Starter Web - biblioteka do tworzenia REST API -> implementation 'org.springframework.boot:spring-boot-starter-web'
        + biblioteki Lombok - pomocnicze biblioteki do pracy z kodem Java -> compileOnly 'org.projectlombok:lombok'
        + sterownik JDBC dla MySQL - sterownik do bazy danych -> runtimeOnly 'com.mysql:mysql-connector-j'
        + Spring Boot Starter Test - biblioteka do testowania: JUnit 5, Mockito, Spring Test, AssertJ, -> testImplementation 'org.springframework.boot:spring-boot-starter-test'
        + Biblioteka do uruchamianie testów JUnit Platform, -> testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
    - Baza danych: MySQL;
    - Narzędzie do testowania API: Postman;
        + POST      localhost:8080/v1/tasks – Tworzenie zadania.
        + GET       localhost:8080/v1/tasks – Pobieranie wszystkich zadań.
        + GET       localhost:8080/v1/tasks/{id} – Pobieranie zadania po ID.
        + PUT       localhost:8080/v1/tasks/{id} – Aktualizacja zadania.
        + DELETE    localhost:8080/v1/tasks/{id} – Usunięcie zadania.

3. Dodatkowe narzędzia i narzędzia do testowania

    - Junit 5
    - Postman
    - MySQL Workbench
    - Trello

4. Możliwe rozszerzenia

        - Mockito;
        - widok w przeglądarce z wykorzystaniem Thymeleaf;
        - HTTP, CSS, JavaScript;

5. Podsumowanie

Aplikacja “Task” to system zarządzania zadaniami, wykorzystujący REST API oraz interfejs użytkownika oparty na widokach HTML.
Aplikacja korzysta z bazy danych MySQL i została zaprojektowana zgodnie z architekturą warstwową, z użyciem Spring Boot.