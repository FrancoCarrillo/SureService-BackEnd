Feature: Put operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "technician/" + technician_id
    * request {"username": "#(username)", "email": "#(email)", "name": "#(name)", "last_name": "#(last_name)", "telephone_number": "#(telephone_number)", "dni": "#(dni)", "professional_profile": "#(professional_profile)", "district": "#(district)", "speciality": "#(sp_id)", "valoration": "#(valoration)", "disponibility": "#(disponibility)"}


  Scenario Outline: : Put reservation
    When method PUT
    Then status 200
    Examples:
      | username  | email               | name  | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | marco_cal | marco.cal@gmail.com | Aldair | Calderon  | 988877654        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |