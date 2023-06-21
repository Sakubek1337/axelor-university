package com.axelor.university.db;

import com.axelor.auth.db.AuditableModel;
import com.axelor.db.annotations.EqualsInclude;
import com.axelor.db.annotations.Widget;
import com.google.common.base.MoreObjects;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(
  name = "UNIVERSITY_STUDENT"
)
public class Student extends AuditableModel {

  @Id
  @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "UNIVERSITY_STUDENT_SEQ"
  )
  @SequenceGenerator(
    name = "UNIVERSITY_STUDENT_SEQ",
    sequenceName = "UNIVERSITY_STUDENT_SEQ",
    allocationSize = 1
  )
  private Long id;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @EqualsInclude
  @NotNull
  @Size(
    max = 100
  )
  @Column(
    unique = true
  )
  private String email;

  @Widget(
    massUpdate = true
  )
  @Size(
    max = 20
  )
  private String phone;

  private LocalDate dateOfBirth;

  @ManyToMany(
    fetch = FetchType.LAZY,
    cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE
    }
  )
  private Set<FacultyGroup> facultyGroups;

  @NotNull
  @OneToMany(
    fetch = FetchType.LAZY,
    mappedBy = "student",
    cascade = CascadeType.ALL,
    orphanRemoval = true
  )
  private List<Address> addresses;

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

  public Student() {
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Set<FacultyGroup> getFacultyGroups() {
    return facultyGroups;
  }

  public void setFacultyGroups(Set<FacultyGroup> facultyGroups) {
    this.facultyGroups = facultyGroups;
  }

  /**
   * Add the given {@link FacultyGroup} item to the {@code facultyGroups} collection.
   *
   * @param item the item to add
   */
  public void addFacultyGroup(FacultyGroup item) {
    if (getFacultyGroups() == null) {
      setFacultyGroups(new HashSet<>());
    }
    getFacultyGroups().add(item);
  }

  /**
   * Remove the given {@link FacultyGroup} item from the {@code facultyGroups} collection.
   *
   * @param item the item to remove
   */
  public void removeFacultyGroup(FacultyGroup item) {
    if (getFacultyGroups() == null) {
      return;
    }
    getFacultyGroups().remove(item);
  }

  /**
   * Clear the {@code facultyGroups} collection.
   */
  public void clearFacultyGroups() {
    if (getFacultyGroups() != null) {
      getFacultyGroups().clear();
    }
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  /**
   * Add the given {@link Address} item to the {@code addresses} collection.
   *
   * <p>
   * It sets {@code item.student = this} to ensure the proper relationship.
   * </p>
   *
   * @param item the item to add
   */
  public void addAddress(Address item) {
    if (getAddresses() == null) {
      setAddresses(new ArrayList<>());
    }
    getAddresses().add(item);
    item.setStudent(this);
  }

  /**
   * Remove the given {@link Address} item from the {@code addresses} collection.
   *
   * @param item the item to remove
   */
  public void removeAddress(Address item) {
    if (getAddresses() == null) {
      return;
    }
    getAddresses().remove(item);
  }

  /**
   * Clear the {@code addresses} collection.
   */
  public void clearAddresses() {
    if (getAddresses() != null) {
      getAddresses().clear();
    }
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
    if (!(obj instanceof Student)) return false;

    final Student other = (Student) obj;
    if (this.getId() != null || other.getId() != null) {
      return Objects.equals(this.getId(), other.getId());
    }

    return Objects.equals(getEmail(), other.getEmail())
      && (getEmail() != null);
  }

  @Override
  public int hashCode() {
    return 31;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
     .add("id", getId())
      .add("firstName", getFirstName())
      .add("lastName", getLastName())
      .add("email", getEmail())
      .add("phone", getPhone())
      .add("dateOfBirth", getDateOfBirth())
      .omitNullValues()
      .toString();
  }
}
