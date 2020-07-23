package com.foodie.foodieApp.entities.enums;

public enum RestauranteTipo {

	CAFE_DA_MANHA(1),
	ALMOCO(2),
	JANTAR(3),
	LANCHE(4);
	
	private int code;
	
	private RestauranteTipo(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static RestauranteTipo valueOf(int code) {
		for (RestauranteTipo value : RestauranteTipo.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo de RestauranteTipo inválido");
	}
}
