package com.codetest.fucheng.phonenumbers.controller;


import com.codetest.fucheng.phonenumbers.api.PhoneNumbersApi;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber;
import com.codetest.fucheng.phonenumbers.service.PhoneNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Fucheng Li
 * @since : 20/01/2022, Mon
 **/

@Validated
@RequestMapping("v1/phoneNumbers")
@Api(tags = {"PhoneNumber"})
@Controller
public class PhoneNumbersController implements PhoneNumbersApi {

  private static final Logger logger = LoggerFactory.getLogger(PhoneNumbersController.class);

  @Autowired
  PhoneNumberService phoneNumberService;

  @GetMapping
  @ResponseBody
  //Todo pagination
  public ResponseEntity<List<PhoneNumber>> findPhoneNumbers() {
    logger.info("[PhoneNumbersController] the method:find all phone numbers is started");
    return new ResponseEntity(phoneNumberService.getAllPhoneNumber(), HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<PhoneNumber> activePhoneNumber(
      @ApiParam(value = "", required = true) @Valid @RequestBody PhoneNumber phoneNumber) {
    logger.info("[PhoneNumbersController] active a phone number is started, the param:{}",
        phoneNumber);
    return new ResponseEntity(phoneNumberService.createPhoneNumber(phoneNumber), HttpStatus.OK);
  }

  @GetMapping(value = "/customer/{customerId}")
  public ResponseEntity<PhoneNumber> getPhoneNumberByCustomerId(
      @ApiParam(value = "", required = true) @PathVariable("customerId") String customerId) {
    logger.info("[PhoneNumbersController] the method:getPhoneNumberByCustomerId is started");
    return new ResponseEntity(phoneNumberService.getPhoneNumbersByCustomerId(customerId),
        HttpStatus.OK);
  }


}
