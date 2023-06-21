package com.axelor.university.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.university.db.Address;

public class AddressRepository extends JpaRepository<Address> {

  public AddressRepository() {
    super(Address.class);
  }
}
