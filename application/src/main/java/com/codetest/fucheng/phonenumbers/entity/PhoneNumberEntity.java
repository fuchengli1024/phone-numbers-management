package com.codetest.fucheng.phonenumbers.entity;

import lombok.Data;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Data
@Entity
@Table(name = "PHONE_NUMBER")
public class PhoneNumberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)

    private long id;

    @Column(name = "PHONE_NUMBER", updatable = false, nullable = false)
    private String phoneNumber;

    @Column(name = "CUSTOMER_ID", updatable = false, nullable = false)
    private String customerId;

    @Column(name = "DESCRIPTION", updatable = false, nullable = false)
    private String description;

    @Column(name = "TYPE", updatable = false, nullable = false)
    private String type;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SIM_SERIAL", updatable = false, nullable = false)
    private String simSerial;



}
