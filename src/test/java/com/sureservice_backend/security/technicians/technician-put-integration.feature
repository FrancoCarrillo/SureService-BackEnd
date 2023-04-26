Feature: Integration test for put technician

  Scenario: Put technician flow
    * def speciality_result = call read("../speciality/get/speciality-get.feature@GetAllSpecialities")
    * def speciality_id = $speciality_result.id
    * def technician1_result = call read("post/technician-post.feature@CreateTechnician1") { specialityId: #(speciality_id) }
    * def technician1_id = $technician1_result.id
    * def technician2_result = call read("post/technician-post.feature@CreateTechnician2") { specialityId: #(speciality_id) }
    * def technician2_id = $technician2_result.id
    * call read("put/technician-put.feature") { technician_id: #(technician1_id) }
    * call read("../users/delete/user-delete.feature@DeleteUser") { user_id: #(technician1_id) }
    * call read("../users/delete/user-delete.feature@DeleteUser") { user_id: #(technician2_id) }