package com.foodie.foodieApp.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String decodeParam(String nome) {
		try {
			return URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
