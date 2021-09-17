package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;

public class PhoneMapper {

    public static PhoneMapper INSTANCE = new PhoneMapper();

    public static Phone phoneEntityToPhone(PhoneEntity phoneEntity) {
        return Phone.builder()
                .id(phoneEntity.getId())
                .type(phoneEntity.getType().getDescription())
                .ddd(phoneEntity.getDdd())
                .number(phoneEntity.getNumber())
                .build();
    }

    public static PhoneEntity phoneToPhoneEntity(Phone phone) {
        return PhoneEntity.builder().id(phone.getId())
                .type(phone.getType())
                .ddd(phone.getDdd())
                .number(phone.getNumber()).build();
    }
}
