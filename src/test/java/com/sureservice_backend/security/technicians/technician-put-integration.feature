Feature: Integration test for put technician

  Scenario: Put technician flow
    * def id = call read("../speciality/get/speciality-get.feature@GetAllSpecialities")
    * def sp_id = $id.id
    * def tid = call read("post/technician-post.feature@CreateTechnician") { specialityId: #(sp_id) }
    * def technician_id = $tid.id
    * call read("put/technician-put.feature") { technician_id: #(technician_id) }
    * call read("../users/delete/user-delete.feature@DeleteUser") { user_id: #(technician_id) }