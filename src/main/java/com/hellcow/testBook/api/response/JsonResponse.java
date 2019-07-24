package com.hellcow.testBook.api.response;

import com.hellcow.testBook.api.exception.ApiException;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;


@Data
public class JsonResponse implements Serializable {
	protected String res_code;
	protected String res_msg;

	public static JsonResponse create() {
		return new JsonBasicResponse();
	}
	public static JsonResponse create(Map<String, Object> map) {
		return new JsonMapResponse(map);
	}
	public static JsonResponse create(Object data) {
		return new JsonObjectResponse(data);
	}

	public static JsonResponse error(String res_code) {
		return new JsonErrorResponse(res_code);
	}
	public static JsonResponse error(String res_code, String res_msg) {
		return new JsonErrorResponse(res_code, res_msg);
	}
	public static JsonResponse error(ApiException apiException) {
		return new JsonErrorResponse(apiException);
	}
	public static JsonResponse error(Exception e) {
        return new JsonErrorResponse(e) ;
    }
}
