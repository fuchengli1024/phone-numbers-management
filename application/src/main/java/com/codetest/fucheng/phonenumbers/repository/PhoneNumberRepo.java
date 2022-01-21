package com.codetest.fucheng.phonenumbers.repository;

import com.codetest.fucheng.phonenumbers.entity.PhoneNumberEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Fucheng Li
 * @since : 20/01/2022, Mon
 **/

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumberEntity, Long> {

  List<PhoneNumberEntity> findAllByCustomerId(String customerId);

}
