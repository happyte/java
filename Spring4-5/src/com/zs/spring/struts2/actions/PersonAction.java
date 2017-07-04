package com.zs.spring.struts2.actions;

import com.zs.spring.struts.service.PersonService;

public class PersonAction {
	private PersonService personService;
	
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	public String execute(){
		System.out.println("execute.......");
		personService.save();
		return "success";
	}
}
