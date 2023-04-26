Feature: Delete operation on SureService API

  @DeleteUser
  Scenario: Delete user
    Given url "http://localhost:8080" + "/api/v1/" + "users/" + id
    When method DELETE
    Then status 204