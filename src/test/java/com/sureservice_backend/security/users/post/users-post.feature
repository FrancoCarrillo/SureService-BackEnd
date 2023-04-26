Feature: Post operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "users/auth/" + "sign-in"
    * request {"username": "#(username)", "password": "#(password)"}

  @SignInUserWithWrongUsername
  Scenario Outline: Sign In user with wrong username
    When method POST
    Then status 400
    And match $ == "An error occurred while authenticating: Bad credentials"

    Examples:
      | username  | password |
      | marco_cal1 | string   |

  @SignInUserWithWrongPassword
  Scenario Outline: Sign In user with wrong password
    When method POST
    Then status 400
    And match $ == "An error occurred while authenticating: Bad credentials"

    Examples:
      | username  | password |
      | marco_cal | string_   |

  @SignInUserWithWrongPassword
  Scenario Outline: Sign In user with wrong password
    When method POST
    Then status 200
    And match $.token == "#string"

    Examples:
      | username  | password |
      | marco_cal | string   |