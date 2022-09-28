Feature: Búsqueda de técnico según la ubicación.
  Scenario: El usuario realiza un filtro de ubicación de los técnicos
    Given que el usuario de la aplicación ha seleccionado el grupo de técnicos especializados
    When despliegue la lista de lugares
    And elija un lugar cercano a su domicilio
    Then la aplicación le mostrara los técnicos en esa ubicación