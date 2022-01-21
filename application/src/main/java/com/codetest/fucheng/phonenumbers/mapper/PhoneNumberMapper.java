package com.codetest.fucheng.phonenumbers.mapper;

import com.codetest.fucheng.phonenumbers.entity.PhoneNumberEntity;
import com.codetest.fucheng.phonenumbers.model.PhoneNumber;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhoneNumberMapper {

    PhoneNumber entityToDTO(PhoneNumberEntity entity);

    PhoneNumberEntity DtoToEntity(PhoneNumber dto);
}
