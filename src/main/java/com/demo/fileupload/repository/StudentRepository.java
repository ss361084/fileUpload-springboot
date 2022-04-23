package com.demo.fileupload.repository;

import com.demo.fileupload.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    boolean existsByNameIgnoreCase(String name);
}
