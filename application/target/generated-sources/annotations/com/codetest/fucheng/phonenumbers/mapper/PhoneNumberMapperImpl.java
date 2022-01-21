package com.codetest.fucheng.phonenumbers.mapper;

import com.codetest.fucheng.phonenumbers.entity.PhoneNumberEntity;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber.StatusEnum;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber.TypeEnum;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-01-21T13:32:05+1100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class PhoneNumberMapperImpl implements PhoneNumberMapper {

    @Override
    public PhoneNumber entityToDTO(PhoneNumberEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneNumber phoneNumber = new PhoneNumber();

        phoneNumber.setId( String.valueOf( entity.getId() ) );
        phoneNumber.setPhoneNumber( entity.getPhoneNumber() );
        phoneNumber.setCustomerId( entity.getCustomerId() );
        phoneNumber.setDescription( entity.getDescription() );
        if ( entity.getType() != null ) {
            phoneNumber.setType( Enum.valueOf( TypeEnum.class, entity.getType() ) );
        }
        if ( entity.getStatus() != null ) {
            phoneNumber.setStatus( Enum.valueOf( StatusEnum.class, entity.getStatus() ) );
        }
        phoneNumber.setSimSerial( entity.getSimSerial() );

        return phoneNumber;
    }

    @Override
    public PhoneNumberEntity DtoToEntity(PhoneNumber dto) {
        if ( dto == null ) {
            return null;
        }

        PhoneNumberEntity phoneNumberEntity = new PhoneNumberEntity();

        if ( dto.getId() != null ) {
            phoneNumberEntity.setId( Long.parseLong( dto.getId() ) );
        }
        phoneNumberEntity.setPhoneNumber( dto.getPhoneNumber() );
        phoneNumberEntity.setCustomerId( dto.getCustomerId() );
        phoneNumberEntity.setDescription( dto.getDescription() );
        if ( dto.getType() != null ) {
            phoneNumberEntity.setType( dto.getType().name() );
        }
        if ( dto.getStatus() != null ) {
            phoneNumberEntity.setStatus( dto.getStatus().name() );
        }
        phoneNumberEntity.setSimSerial( dto.getSimSerial() );

        return phoneNumberEntity;
    }
}
