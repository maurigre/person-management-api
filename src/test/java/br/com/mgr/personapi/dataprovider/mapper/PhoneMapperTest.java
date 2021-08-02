package br.com.mgr.personapi.dataprovider.mapper;

import br.com.mgr.personapi.core.entity.Person;
import br.com.mgr.personapi.core.entity.Phone;
import br.com.mgr.personapi.core.entity.PhoneType;
import br.com.mgr.personapi.dataprovider.model.PersonEntity;
import br.com.mgr.personapi.dataprovider.model.PhoneEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneMapperTest {



    private final Long ID = 1L;
    private final PhoneType TYPE = PhoneType.COMMERCIAL;
    private final String NUMBER = "16999994444";
    private final Phone PHONES =   new Phone(ID, TYPE, NUMBER);


    @Test
    @DisplayName("Deve ser possivel instanciar o objeto PhoneMapper pelo construtor")
    public void shouldInstanceObjectPhoneMapper(){

        final PhoneMapper phoneMapper = new PhoneMapper();

        assertThat(phoneMapper).isNotNull();
        assertThat(phoneMapper).isInstanceOf(PhoneMapper.class);
    }

    @Test
    @DisplayName("Deve receber objeto PhoneEntity and retornar objeto Phone")
    public void shouldReceivePhoneEntityAndReturnPhone(){
        final PhoneEntity phoneEntity = PhoneEntity.builder()
                .id(ID)
                .type(TYPE)
                .number(NUMBER)
                .build();

        final Phone phone = PhoneMapper.phoneEntityToPhone(phoneEntity);

        assertThat(phoneEntity.getId()).isEqualTo(phone.getId());
        assertThat(phoneEntity.getType()).isEqualByComparingTo(phone.getType());
        assertThat(phoneEntity.getNumber()).isEqualTo(phone.getNumber());
        assertThat(PhoneEntity.builder().toString()).isNotEmpty();
    }

    @Test
    @DisplayName("Deve receber objeto Phone and retornar objeto PhoneEntity")
    public void shouldReceivePhoneAndReturnPhoneEntity(){

        final Phone phone = PHONES;

        final PhoneEntity phoneEntity = PhoneMapper.phoneToPhoneEntity(phone);

        assertThat(phone.getId()).isEqualTo(phoneEntity.getId());
        assertThat(phone.getType()).isEqualByComparingTo(phoneEntity.getType());
        assertThat(phone.getNumber()).isEqualTo(phoneEntity.getNumber());


    }

}
