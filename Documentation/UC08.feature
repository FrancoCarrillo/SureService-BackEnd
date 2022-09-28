Feature: Información detallada del técnico
  Scenario: Usuario visualiza la información detallada de un técnico
    Given que el usuario de la aplicación ya se encuentre dentro una sección de técnicos especializado
    When haga click en el técnico que le parece más apropiado
    Then la página web, le redirigirá a una página donde se visualizará la información del técnico seleccionado