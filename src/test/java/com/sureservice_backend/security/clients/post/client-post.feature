Feature: Post operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "clients/" + "sign-up"
    * request { "username": "#(username)", "email": "#(email)", "password": "#(password)", "name": "#(name)", "last_name": "#(last_name)", "telephone_number": "#(telephone_number)", "dni": "#(dni)" }

  @CreateUserWithWrongEmail
  Scenario Outline: Post user with wrong email
    When method POST
    Then status 400
    And match  $.errors[:1].defaultMessage == ["Email not valid"]

    Examples:
      | username | email              | password | name  | last_name | telephone_number | dni
      | francocg | marco.sahotmailcom | string   | Marco | Salas     | 933344737        | 74657574

  @CreateUserWithWrongPhone
  Scenario Outline: Post user with wrong phone number
    When method POST
    Then status 400
    And match  $.errors[:1].defaultMessage == ["Wrong phone number"]

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni
      | franco_cg | marco.sa@hotmail.com | string   | Marco | Salas     | 988877           | 74657574

  @CreateUserWithWrongDni
  Scenario Outline: Post user with wrong dni
    When method POST
    Then status 400
    And match  $.errors[:1].defaultMessage == ["Wrong DNI"]

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni
      | franco_cg | marco.sa@hotmail.com | string   | Marco | Salas     | 988877676        | 98765

  @CreateUserWithWrongPassword
  Scenario Outline: Post user with wrong password
    When method POST
    Then status 400
    And match  $.errors[:1].defaultMessage == ["Wrong password lenght"]

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni
      | franco_cg | marco.sa@hotmail.com | hola     | Marco | Salas     | 988877676        | 84758484

  @CreateUser
  Scenario Outline: Post user
    When method POST
    Then status 201
    * def id = $.id

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni      | id                          |
      | franco_cg | marco.sa@hotmail.com | string   | Marco | Salas     | 988877676        | 84758484 | <eval> karate.set('id', id) |

  Scenario Outline: Post user with duplicate username error
    When method POST
    Then status 400
    And match  $.message == "Username is already used."

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni
      | francocg | marco.sa@hotmail.com | string   | Marco | Salas     | 988877676        | 84758484

  Scenario Outline: Post user with duplicate email error
    When method POST
    Then status 400
    And match  $.message == "Email is already used."

    Examples:
      | username | email                | password | name  | last_name | telephone_number | dni
      | arcg2424 | marco.sa@hotmail.com | string   | Marco | Salas     | 988877676        | 84758484

  Scenario Outline: Post user with duplicate dni error
    When method POST
    Then status 400
    And match  $.message == "DNI is already used."

    Examples:
      | username | email                 | password | name  | last_name | telephone_number | dni
      | arcg2424 | marco.sa1@hotmail.com | string   | Marco | Salas     | 988877676        | 84758484

  Scenario Outline: Post user with duplicate phone number error
    When method POST
    Then status 400
    And match  $.message == "Telephone number is already used."

    Examples:
      | username | email                 | password | name  | last_name | telephone_number | dni
      | arcg2424 | marco.sa1@hotmail.com | string   | Marco | Salas     | 988877676        | 84758483