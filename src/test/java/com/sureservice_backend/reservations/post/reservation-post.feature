Feature: Post operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "reservations/" + "2"
    * request { "date_of": "#(date_of)" }

  Scenario Outline: Post reservation
    When method POST
    Then status 201

    Examples:
      | date_of                  |
      | 2023-04-25T18:58:11.509Z |
      | 2023-04-25T20:58:11.509Z |
