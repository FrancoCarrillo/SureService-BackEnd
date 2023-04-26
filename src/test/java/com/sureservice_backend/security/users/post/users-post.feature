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
      | username | password |
      | username | password |

  @SignInUserWithWrongPassword
  Scenario Outline: Sign In user with wrong password
    When method POST
    Then status 400
    And match $ == "An error occurred while authenticating: Bad credentials"

    Examples:
      | username | password |
      | username | password |