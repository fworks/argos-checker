package fworks.argos.checker.model;

import com.google.api.client.util.Key;

public class Store {

	@Key("code")
	private Integer code;
	@Key("name")
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}