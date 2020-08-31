package com.foodie.foodieApp.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class URL {

	public static String decodeParam(String titulo) {
		try {
			return URLDecoder.decode(titulo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
