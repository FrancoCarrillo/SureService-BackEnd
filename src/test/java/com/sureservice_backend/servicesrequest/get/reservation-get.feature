Feature: Get reservation operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "reservations"

  Scenario: Get all reservations
    When method GET
    Then status 200

  Scenario: Get reservations by id
    Given url "/1"
    When method GET
    Then status 200