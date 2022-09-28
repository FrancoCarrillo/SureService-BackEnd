Feature: Solicitar servicio
  Scenario: Usuario solicita servicio de un técnico
    Given que el usuario de la aplicación ya ha elegido al técnico más adecuado para su problema
    When presione el botón de ‘Solicitar servicio’
    And rellene los campos para la solicitud
    And presiones el botón ‘Enviar’
    Then le aparecerá un mensaje de solicitud enviada exitosamente