package com.bss.databaseBean;

import java.util.List;

import com.bss.jsonview.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class BssResponseBody {

	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<CommonLogin> result;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CommonLogin> getResult() {
		return result;
	}

	public void setResult(List<CommonLogin> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "BssResponseResult [msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}
}
