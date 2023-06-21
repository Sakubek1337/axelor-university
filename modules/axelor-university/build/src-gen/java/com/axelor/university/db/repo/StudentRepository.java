package com.axelor.university.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.university.db.Student;

public class StudentRepository extends JpaRepository<Student> {

  public StudentRepository() {
    super(Student.class);
  }
}
