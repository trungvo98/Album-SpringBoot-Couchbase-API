package com.lugd.album.utilities;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author DATPV
 * @version 1.0
 * @since 24/05/2019
 */
public class AppUtil {

	public static Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {

		Map<String, String> result = new HashMap<>();

		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			result.put(key, value);
		}

		return result;
	}

	public static String getIPByMapRequest(HttpServletRequest request) {
		String ip = "";
		try {
			Map<String, String> map = getRequestHeadersInMap(request);
			ip = map.get("host");
		} catch (Exception e) {
			ip = "0.0.0.0";// Cannot get ip address
		}

		return ip;
	}

}
