package com.axelor.university.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "UNIVERSITY_FACULTY_GROUP",
  indexes = @Index(
    columnList = "name"
  )
)
public class FacultyGroup extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "UNIVERSITY_FACULTY_GROUP_SEQ"
  )
  @SequenceGenerator(
    name = "UNIVERSITY_FACULTY_GROUP_SEQ",
    sequenceName = "UNIVERSITY_FACULTY_GROUP_SEQ",
    allocationSize = 1
  )
  private Long id;

  @ManyToMany(
    fetch = FetchType.LAZY,
    mappedBy = "facultyGroups",
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<Student> students;

  @NotNull
  private String faculty;

  @NotNull
  private Integer maxStudents = 0;

  @NotNull
  private String room;

  @NotNull
  private Boolean finished = Boolean.FALSE;

  @NotNull
  private String name;

  @Widget(
    title = "Attributes"
  )
  @Basic(
    fetch = FetchType.LAZY
  )
  @Type(
    type = "json"
  )
  private String attrs;

  public FacultyGroup() {
  }

  public FacultyGroup(String name) {
    this.name = name;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public Set<Student> getStudents() {
    return students;
  }

  public void setStudents(Set<Student> students) {
    this.students = students;
  }

  /**
   * Add the given {@link Student} item to the {@code students} collection.
   *
   * @param item the item to add
   */
  public void addStudent(Student item) {
    if (getStudents() == null) {
      setStudents(new HashSet<>());
    }
    getStudents().add(item);
  }

  /**
   * Remove the given {@link Student} item from the {@code students} collection.
   *
   * @param item the item to remove
   */
  public void removeStudent(Student item) {
    if (getStudents() == null) {
      return;
    }
    getStudents().remove(item);
  }

  /**
   * Clear the {@code students} collection.
   */
  public void clearStudents() {
    if (getStudents() != null) {
      getStudents().clear();
    }
  }

  public String getFaculty() {
    return faculty;
  }

  public void setFaculty(String faculty) {
    this.faculty = faculty;
  }

  public Integer getMaxStudents() {
    return maxStudents == null ? 0 : maxStudents;
  }

  public void setMaxStudents(Integer maxStudents) {
    this.maxStudents = maxStudents;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public Boolean getFinished() {
    return finished == null ? Boolean.FALSE : finished;
  }

  public void setFinished(Boolean finished) {
    this.finished = finished;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAttrs() {
    return attrs;
  }

  public void setAttrs(String attrs) {
    this.attrs = attrs;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (this == obj) return true;
    if (!(obj instanceof FacultyGroup)) return false;

    final FacultyGroup other = (FacultyGroup) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return false;
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("faculty", getFaculty())
      .add("maxStudents", getMaxStudents())
      .add("room", getRoom())
      .add("finished", getFinished())
      .add("name", getName())
      .omitNullValues()
      .toString();
  }
}
