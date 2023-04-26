Feature: Integration test for create user

  @CreateReservation
  Scenario: Post flow
    * call read("client-post.feature@CreateUserWithWrongEmail")
    * call read("client-post.feature@CreateUserWithWrongPhone")
    * call read("client-post.feature@CreateUserWithWrongDni")
    * call read("client-post.feature@CreateUserWithWrongPassword")
    * def id = call read("client-post.feature@CreateUser")
    * call read("../../user-delete.feature@DeleteUser") id