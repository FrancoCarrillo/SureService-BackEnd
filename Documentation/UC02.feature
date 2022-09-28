Feature: Búsqueda de técnico según la valoración.
  Scenario: El usuario realiza un ordenamiento de los técnicos según valoración 
    Given que el usuario de la aplicación ha seleccionado el grupo de técnicos especializados
    When presione el botón de ordenamiento, al costado del nombre de la columna ‘valoración’
    Then la aplicación ordenara a los técnicos de mayor a menor, según su valoración.