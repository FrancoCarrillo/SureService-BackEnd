Feature: Detalle del servicio técnico
  Scenario: Técnico visualiza el detalle del servicio solicitado
    Given que el usuario técnico se encuentre dentro de la sección solicitudes de servicio
    When haga click en alguna de ellas
    Then le aparecerá el detalle del servicio solicitado.
