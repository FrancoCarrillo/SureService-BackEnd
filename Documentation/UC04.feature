Feature: Búsqueda de técnico según la disponibilidad.
  Scenario: El usuario realiza un filtro de disponibilidad de los técnicos
    Given que el usuario de la aplicación ha seleccionado el grupo de técnicos especializados
    When haga switch en el toggle de disponibilidad
    Then la aplicación le mostrara los técnicos disponibles.