Feature: Confirmación de pago realizado
  Scenario: El usuario visualiza la notificación de pago en su correo
    Given que el usuario de la aplicación ya ha realizado el pago
    When ingrese a su correo electrónico
    Then le aparecerá un correo con detalles del pago que ha realizado