package com.hellcow.testBook.api.response;

import com.hellcow.testBook.api.config.ApplicationContextManager;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;

public class JsonObjectResponse extends JsonResponse {
	private MessageSourceAccessor accessor;

	private Object data;

	public JsonObjectResponse(Object obj) {
		accessor = (MessageSourceAccessor) ApplicationContextManager.getBean("messageSourceAccessor");
		res_code = String.valueOf(HttpStatus.OK.value());
		res_msg = accessor.getMessage(this.res_code);
		this.data = obj;
	}

	public Object getData() {
		return this.data;
	}
}
