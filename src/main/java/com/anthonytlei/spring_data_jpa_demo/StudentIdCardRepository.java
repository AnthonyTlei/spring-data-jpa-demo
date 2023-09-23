package com.anthonytlei.spring_data_jpa_demo;

import org.springframework.data.repository.CrudRepository;

public interface StudentIdCardRepository extends CrudRepository<StudentIdCard, Long>{
    
}
