Feature: Delete operation on SureService API

  Scenario: Delete reservation
    * call read("../post/reservation_post_snippet.feature@CreateReservation")
    Given url "http://localhost:8080" + "/api/v1/" + "reservations/" + reservationId
    When method DELETE
    Then status 204