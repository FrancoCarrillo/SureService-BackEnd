Feature: Integration test for put technician

  Scenario: Put technician flow
    * def id = call read("../speciality/get/speciality-get.feature@GetAllSpecialities")
    * def sp_id = $id.id
    * def technician_id = call read("post/technician-post.feature@CreateTechnician") [sp_id]
    * call read("../user-delete.feature@DeleteUser") technician_id