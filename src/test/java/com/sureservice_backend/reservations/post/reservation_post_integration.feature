Feature: Integration test for create user

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "clients/" + "sign-up"

  @CreateReservation
  Scenario: Post reservation
    When method POST
    Then status 201
    And def reservationId = $.id