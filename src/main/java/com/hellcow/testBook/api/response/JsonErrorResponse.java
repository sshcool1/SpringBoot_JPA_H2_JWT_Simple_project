package com.hellcow.testBook.api.response;


import com.hellcow.testBook.api.config.ApplicationContextManager;
import com.hellcow.testBook.api.exception.ApiException;
import org.springframework.context.support.MessageSourceAccessor;

public class JsonErrorResponse extends JsonResponse {
    private MessageSourceAccessor accessor;

    public JsonErrorResponse(String res_code) {
        accessor = (MessageSourceAccessor) ApplicationContextManager.getBean("messageSourceAccessor");
        this.res_code = res_code ;
        this.res_msg = accessor.getMessage(res_code);

    }

    public JsonErrorResponse(String res_code, String res_msg) {
        this.res_code = res_code ;
        this.res_msg = res_msg;
    }

    public JsonErrorResponse(ApiException apiException) {
    	accessor = (MessageSourceAccessor) ApplicationContextManager.getBean("messageSourceAccessor");
        this.res_code = apiException.getRes_code() ;
        this.res_msg = accessor.getMessage(apiException.getRes_code());
    }

    public JsonErrorResponse(Exception e) {
        if(e instanceof ApiException){
             this.res_code = ((ApiException) e).getRes_code() ;
        }else{
            this.res_code = "500" ;
        }
        this.res_msg = e.getMessage() ;
    }
}
