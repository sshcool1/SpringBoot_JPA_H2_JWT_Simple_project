package com.hellcow.testBook.api.response;

import com.hellcow.testBook.api.config.ApplicationContextManager;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;

public class JsonBasicResponse extends JsonResponse {
    private MessageSourceAccessor accessor;

    public JsonBasicResponse() {
        accessor = (MessageSourceAccessor) ApplicationContextManager.getBean("messageSourceAccessor");
        res_code = HttpStatus.OK.toString();
        res_msg = accessor.getMessage(res_code);
    }
}
