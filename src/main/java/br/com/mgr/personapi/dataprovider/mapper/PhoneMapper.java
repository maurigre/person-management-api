package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;

public class PhoneMapper {



    public static Phone phoneEntityToPhone(PhoneEntity phoneEntity) {
        return new Phone(phoneEntity.getId(), phoneEntity.getType(), phoneEntity.getNumber());
    }

    public static PhoneEntity phoneToPhoneEntity(Phone phone) {
        return PhoneEntity.builder().id(phone.getId())
                .type(phone.getType())
                .number(phone.getNumber()).build();
    }
}
