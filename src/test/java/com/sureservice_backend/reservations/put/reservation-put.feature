Feature: Put operation on SureService API

  Scenario: Put reservation
    * def jsonRequest = read("../jsons/put_reservation.json")
    Given url "http://localhost:8080" + "/api/v1/" + "reservations/" + "2"
    And request jsonRequest
    When method PUT
    Then status 200
    And match $.status == 1