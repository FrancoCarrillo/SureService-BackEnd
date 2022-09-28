Feature: Información del cliente
  Scenario: Técnico visualiza la información detallada del cliente
    Given que el usuario técnico se encuentre dentro de la sección solicitudes de servicio
    When haga click en alguna de ellas
    And haga click en el usuario que ha solicitado el servicio
    Then le aparecerá la información del usuario detallada.
