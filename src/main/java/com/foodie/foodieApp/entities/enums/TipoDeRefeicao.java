package com.foodie.foodieApp.entities.enums;

public enum TipoDeRefeicao {

	CAFE_DA_MANHA(1, "Café da Manhã"),
	ALMOCO(2, "Almoço"),
	JANTAR(3, "Jantar"),
	LANCHE(4, "Lanche");

	private int cod;
	private String descricao;
	
	private TipoDeRefeicao(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	public static TipoDeRefeicao toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoDeRefeicao x : TipoDeRefeicao.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
