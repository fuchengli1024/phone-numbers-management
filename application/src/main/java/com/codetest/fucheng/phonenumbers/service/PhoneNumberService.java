package com.codetest.fucheng.phonenumbers.service;

import com.codetest.fucheng.phonenumbers.mapper.PhoneNumberMapper;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber.StatusEnum;
import com.codetest.fucheng.phonenumbers.repository.PhoneNumberRepo;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;

/**
 * @author : Fucheng Li
 * @since : 20/01/2022, Mon
 **/

  @Service
  public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepo phoneNumberRepo;


    @Autowired
    private PhoneNumberMapper phoneNumberMapper;



    /**
     * Get The List  PhoneNumber
     *
     * @return
     */
    public List<PhoneNumber> getAllPhoneNumber() {
      return phoneNumberRepo.findAll().stream()
          .map(phoneNumberEntity -> phoneNumberMapper.entityToDTO(phoneNumberEntity))
          .collect(Collectors.toList());
    }

    /**
     * Lists the phone number by the customerId
     *
     * @param customerId
     * @return
     */
    public List<PhoneNumber> getPhoneNumbersByCustomerId(String customerId) {
      return phoneNumberRepo.findAllByCustomerId(customerId).stream()
          .map(phoneNumberEntity -> phoneNumberMapper.entityToDTO(phoneNumberEntity))
          .collect(Collectors.toList());
    }


    public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber){
      phoneNumberRepo.save(phoneNumberMapper.DtoToEntity(phoneNumber));
      phoneNumber.setStatus(StatusEnum.PROCESSING);
      return phoneNumber;

    }

  }
