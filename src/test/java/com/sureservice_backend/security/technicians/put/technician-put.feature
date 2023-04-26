Feature: Put operation on SureService API

  Background:
    * url "http://localhost:8080" + "/api/v1/" + "technician/" + technician_id
    * request {"username": "#(username)", "email": "#(email)", "name": "#(name)", "last_name": "#(last_name)", "telephone_number": "#(telephone_number)", "dni": "#(dni)", "professional_profile": "#(professional_profile)", "district": "#(district)", "speciality": "#(speciality_id)", "valoration": "#(valoration)", "disponibility": "#(disponibility)"}


  @PutTechnicianWithWrongUsername
  Scenario Outline: : Put technician with wrong username
    When method PUT
    Then status 400
    And match $.message == "Username is already used."
    Examples:
      | username | email               | name   | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | paco_cd  | marco.cal@gmail.com | Aldair | Calderon  | 988877654        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |

  @PutTechnicianWithWrongEmail
  Scenario Outline: : Put technician with wrong email
    When method PUT
    Then status 400
    And match $.message == "Email is already used."
    Examples:
      | username  | email             | name   | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | marco_cal | paco_cd@gmail.com | Aldair | Calderon  | 988877654        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |

  @PutTechnicianWithWrongPhone
  Scenario Outline: : Put technician with wrong phone number
    When method PUT
    Then status 400
    And match $.message == "Phone number is already used."
    Examples:
      | username  | email               | name   | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | marco_cal | marco.cal@gmail.com | Aldair | Calderon  | 977766565        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |

  @PutTechnicianWithWrongDni
  Scenario Outline: : Put technician with wrong dni
    When method PUT
    Then status 400
    And match $.message == "Dni is already used."
    Examples:
      | username  | email               | name   | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | marco_cal | marco.cal@gmail.com | Aldair | Calderon  | 988877654        | 87876776 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |

  @PutTechnician
  Scenario Outline: Put technician
    When method PUT
    Then status 200
    Examples:
      | username  | email               | name   | last_name | telephone_number | dni      | professional_profile                                          | district   | valoration | disponibility |
      | marco_cal | marco.cal@gmail.com | Aldair | Calderon  | 988877654        | 87677684 | Tecnico con varios años de experiencia en el sector electrico | Los Olivos | 3          | 1             |