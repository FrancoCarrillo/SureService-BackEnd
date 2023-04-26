Feature: Get specialities operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "speciality"

  @GetAllSpecialities
  Scenario Outline: Get all specialities
    When method GET
    Then status 200
    * def id = $[0].id
    Examples:
      | id                          |
      | <eval> karate.set('id', id) |