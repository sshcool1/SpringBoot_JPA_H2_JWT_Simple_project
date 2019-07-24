package com.hellcow.testBook.api.response;

import com.hellcow.testBook.api.config.ApplicationContextManager;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class JsonMapResponse extends JsonResponse {
	private MessageSourceAccessor accessor;

	private Map<String, Object> data;

	public JsonMapResponse(Map<String, Object> map) {
		accessor = (MessageSourceAccessor) ApplicationContextManager.getBean("messageSourceAccessor");
		res_code = HttpStatus.OK.toString();;
		res_msg = accessor.getMessage(res_code);
		this.data = map;
	}

	public Map<String, Object> getData() {
		return data;
	}
}
