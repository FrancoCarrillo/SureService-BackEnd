Feature: Post operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "technician/" + "sign-up"
    * request { "username": "#(username)", "email": "#(email)", "password": "#(password)", "name": "#(name)", "last_name": "#(last_name)", "telephone_number": "#(telephone_number)", "dni": "#(dni)", "professional_profile": "#(professional_profile)", "district": "#(district)", "speciality": "#(sp_id)" }

  @CreateTechnician
  Scenario Outline: Post technician
    When method POST
    Then status 201
    * def id = $.id

    Examples:
      | username  | email               | password | name  | last_name | telephone_number | dni      | professional_profile                                          | district   | id                          |
      | marco_cal | marco.cal@gmail.com | string   | Marco | Calderon  | 988877654        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | <eval> karate.set('id', id) |

