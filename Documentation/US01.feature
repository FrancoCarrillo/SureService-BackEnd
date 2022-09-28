Feature: Realizar autenticación al inicio de sesión 
  Scenario: Usuario inicia sesión en la aplicación
    Given que el usuario de la aplicación se encuentra en la pagina de inicio de sesión 
    When haga rellene los inputs 
    And presione el botón de inicio de sesión.
    Then la aplicación se logueara correctamente
