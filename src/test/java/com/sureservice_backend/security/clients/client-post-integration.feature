Feature: Integration test for post client

  Scenario: Post client flow
    * call read("post/client-post.feature@CreateUserWithWrongEmail")
    * call read("post/client-post.feature@CreateUserWithWrongPhone")
    * call read("post/client-post.feature@CreateUserWithWrongDni")
    * call read("post/client-post.feature@CreateUserWithWrongPassword")
    * def id = call read("post/client-post.feature@CreateUser")
    * def user_id = $id.id
    * call read("../users/delete/user-delete.feature@DeleteUser") { user_id: #(user_id) }
