package com.axelor.university.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.university.db.FacultyGroup;

public class FacultyGroupRepository extends JpaRepository<FacultyGroup> {

  public FacultyGroupRepository() {
    super(FacultyGroup.class);
  }

  public FacultyGroup findByName(String name) {
    return Query.of(FacultyGroup.class)
      .filter("self.name = :name")
      .bind("name", name)
      .fetchOne();
  }
}
