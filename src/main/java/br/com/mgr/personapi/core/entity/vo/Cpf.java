package br.com.mgr.personapi.core.entity.vo;

import br.com.mgr.personapi.core.common.Constraints;

public class Cpf {

	private final String number;

	private Cpf(String number) {
		if (number.isBlank()
				|| !number.matches(Constraints.CPF_REGEX)) {
			throw new IllegalArgumentException("CPF invalid!");
		}
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}

	public static Cpf valueOf(String number){
		return new Cpf(number);
	}

	@Override
	public String toString() {
		return number;
	}
}
