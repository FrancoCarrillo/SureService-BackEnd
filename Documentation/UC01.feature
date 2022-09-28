Feature: Dirigir a la sección de un grupo de técnicos especializados 
  Scenario: El usuario se dirige a una sección de técnicos especializados 
    Given que el usuario de la aplicación está en la sección inicial
    When haga click en el card del técnico especializado que requiere
    Then la aplicación lo redirigirá a la página de todos los técnicos especializados seleccionados