Feature: Eliminar cuenta
  Scenario: Eliminar la cuenta
    Given que el usuario de la aplicación se encuentra en los ajustes de su cuenta
    When haga click en eliminar cuenta
    And rellene los datos de confirmación
    Then su cuenta se eliminará correctamente.
