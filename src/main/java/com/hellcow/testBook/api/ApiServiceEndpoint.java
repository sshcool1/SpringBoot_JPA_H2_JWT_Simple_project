package com.hellcow.testBook.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellcow.testBook.api.exception.ApiException;
import com.hellcow.testBook.api.model.ApiData;
import com.hellcow.testBook.api.response.JsonResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class ApiServiceEndpoint {
	private static Logger log = LogManager.getLogger(ApiServiceEndpoint.class);

	@Resource(name = "messageSourceAccessor")
	protected MessageSourceAccessor accessor;
	@Autowired
	private ApplicationContext appContext;

	@POST
	@Path("/{serviceName}/{methodName}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response apiEndPoint(@PathParam("serviceName") String serviceName, @PathParam("methodName") String methodName, InputStream incomingData){
		JsonResponse result = new JsonResponse();
		ApiData apiData = new ApiData();
		log.info("API SERVICE START : " + serviceName + " : " + methodName);

		StringBuilder paramBuilder = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();

		int httpStatus = HttpStatus.OK.value();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData, "UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				paramBuilder.append(line);
			}
			Map reqMap = new HashMap<String, Object>();
			if(paramBuilder.length() > 0) {
				reqMap = mapper.readValue(paramBuilder.toString(), new TypeReference<Map<String, Object>>() {});
			}
			apiData.setData(reqMap);

			Object obj = appContext.getBean(serviceName);
			Method method = this.getTargetMethod(obj, methodName);
			Object invokeData = method.invoke(obj, apiData);
			if(invokeData == null){
				result = JsonResponse.create();
			}else{
				result = JsonResponse.create(invokeData);
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			JsonResponse jsonResponse = JsonResponse.create();
			try {
				throw e.getCause();
			} catch (ApiException ex) {
				httpStatus = ex.getHttpStatus();
				jsonResponse.setRes_code(ex.getRes_code());
				jsonResponse.setRes_msg(ex.getRes_msg());
			} catch (Throwable throwable) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
				String errCode = String.valueOf(httpStatus);
				jsonResponse = JsonResponse.error(errCode, accessor.getMessage(errCode));
			}
			result = jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
			String errCode = String.valueOf(httpStatus);
			result = JsonResponse.error(errCode, accessor.getMessage(errCode));
		}

		return Response.status(httpStatus).entity(result).build();
	}

	// api 호출 대상 메소드 찾기
	private Method getTargetMethod(Object obj, String methodName) throws NoSuchMethodException, SecurityException {
		Method[] methods = obj.getClass().getDeclaredMethods();
		Method targetMethod = null;

		// 어노테이션 이름으로 찾기
		for (Method method : methods) {
			if(method.isAnnotationPresent(ApiMethod.class)) {
				ApiMethod apiMethod = method.getAnnotation(ApiMethod.class);
				if(apiMethod != null) {
					if(methodName.equals(apiMethod.name())) {
						for(Class<?> cls : method.getParameterTypes()) {
							if(ApiData.class.getName().equals(cls.getName())) {
								targetMethod = method;
								break;
							}
						}
					}
				}
			}
		}

		// 메소드 명으로 찾기
		if(targetMethod == null) {
			targetMethod = obj.getClass().getMethod(methodName, ApiData.class);
		}

		return targetMethod;
	}
}
