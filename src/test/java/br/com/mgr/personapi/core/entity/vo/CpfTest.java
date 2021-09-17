package br.com.mgr.personapi.core.entity.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CpfTest {

	private final String CPF =  "310.297.188-14";

	@Test
	void shouldPassedCpfValidAndReturnCpf(){
		Cpf cpf = Cpf.valueOf(CPF);
		assertEquals(CPF, cpf.getNumber());
		assertEquals(CPF, cpf.toString());
	}

	@Test
	void shouldPassedCpfInvalidAndReturnIllegalArgumentException(){
		String message = "CPF invalid!";

		assertThrows(IllegalArgumentException.class, () -> Cpf.valueOf(""), message);
		assertThrows(IllegalArgumentException.class, () -> Cpf.valueOf(" "), message);
		assertThrows(IllegalArgumentException.class, () -> Cpf.valueOf(" A"), message);
		assertThrows(IllegalArgumentException.class, () -> Cpf.valueOf(" A "), message);

	}

	@Test
	void shouldPassedCpfNotFormatedAndReturnIllegalArgumentException(){
		String cpfNotFormated = CPF.replace("-", ".")
				.replace(".", "");

		String message = "CPF invalid!";

		assertThrows(IllegalArgumentException.class, () -> Cpf.valueOf(cpfNotFormated), message);
	}
}
