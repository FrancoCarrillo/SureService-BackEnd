Feature: Sistemas de pagos 
  Scenario: El usuario paga el cupo de separación del servicio técnico. 
    Given que el técnico ya ha respondido el servicio técnico solicitado por el usuario
    When el usuario de la aplicación entre en su solicitud
    And presiona en separar cupo
    And rellene los campos para realizar el pago
    Then le aparecerá un mensaje de ‘Pago exitoso’