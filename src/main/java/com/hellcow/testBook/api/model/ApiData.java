package com.hellcow.testBook.api.model;

import java.util.HashMap;
import java.util.Map;

public class ApiData {

	private Map<String, Object> data;

	public ApiData(Map<String, Object> data) {
		this.data = data;
	}

	public ApiData() {
		this.data = new HashMap();
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public void error(String errCode, String errMsg) {
		this.data.put("res_code", errCode);
		this.data.put("res_msg", errMsg);
	}
}
