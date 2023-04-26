Feature: Integration test for put technician

  Scenario: Put technician flow
    * def speciality_result = call read("../speciality/get/speciality-get.feature@GetAllSpecialities")
    * def speciality_id = $speciality_result.id
    * def technician_result = call read("post/technician-post.feature@CreateTechnician") { specialityId: #(speciality_id) }
    * def technician_id = $technician_result.id
    * call read("put/technician-put.feature@PutTechnician") { technician_id: #(technician_id) }
    * call read("../users/delete/user-delete.feature@DeleteUser") { user_id: #(technician_id) }