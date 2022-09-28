Feature: Valoración de servicio
  Scenario: El usuario valora el servicio ofrecido por el técnico
    Given que el usuario de la aplicación se encuentre en la página principal de la aplicación
    When haga click en el botón de notificación
    And selección la notificación para valorar al técnico
    And le da un puntaje al técnico
    Then le aparecerá un mensaje de agradecimiento por su colaboración.