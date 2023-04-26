Feature: Integration test for sign in users

  Scenario: Sign in users flow
    * def speciality_result = call read("../speciality/get/speciality-get.feature@GetAllSpecialities")
    * def speciality_id = $speciality_result.id
    * def technician_result = call read("../technicians/post/technician-post.feature@CreateTechnician") { specialityId: #(speciality_id) }
    * def technician_id = $technician_result.id
    * call read("post/users-post.feature")
    * call read("delete/user-delete.feature@DeleteUser") { user_id: #(technician_id) }