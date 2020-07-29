package com.foodie.foodieApp.entities.enums;

public enum TipoDeRefeicao {

	CAFE_DA_MANHA(1),
	ALMOCO(2),
	JANTAR(3),
	LANCHE(4);

	private int code;

	private TipoDeRefeicao(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TipoDeRefeicao toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (TipoDeRefeicao x : TipoDeRefeicao.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido: " + code);
	}

	public static TipoDeRefeicao valueOf(int code) {
		for (TipoDeRefeicao value : TipoDeRefeicao.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de TipoDeRefeicao inválido");
	}

}
