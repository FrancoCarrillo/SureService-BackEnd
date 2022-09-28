Feature: Notificación de servicio solicitado
  Scenario: El técnico visualiza la notificación de solicitud de servicio
    Given que el usuario técnico tiene abierto una pestaña del browser
    When ingrese a su correo electrónico
    Then le aparecerá las solicitudes de servicio, con detalles del mismo
