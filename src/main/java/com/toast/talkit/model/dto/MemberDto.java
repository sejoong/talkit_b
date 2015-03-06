package com.toast.talkit.model.dto;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

@Component
public class MemberDto {

	private String id;
	private String email;
	private String name;
	private String password;
	private String registerDate;
	private String updateDate;	

	public static MemberDto intiPaycoMember(String id, String email){
		MemberDto newMember = new MemberDto();
		newMember.setId(id);
		newMember.setEmail(email);
		
		return newMember;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public void bindingData(Map bindingDatas){
		for(Field field : this.getClass().getFields()){
			if(bindingDatas.containsKey(field.getName())){
				try {
					field.set(this, bindingDatas.get(field.getName()));
				} catch (Exception e) {
					e.getCause().printStackTrace();
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
