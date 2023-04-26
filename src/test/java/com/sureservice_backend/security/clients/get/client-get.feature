Feature: Get client operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "clients"

  Scenario: Get all clients
    When method GET
    Then status 200

  Scenario: Get clients by id
    Given url "/1"
    When method GET
    Then status 200

Feature: Reusable post operation on SureService API

  Background:
    * url "http://localhost:8080/api/v1/reservations/2"
    * request { "date_of": "2023-04-25T18:58:11.509Z" }

  @CreateReservation
  Scenario: Post reservation
    When method POST
    Then status 201
    And def reservationId = $.id