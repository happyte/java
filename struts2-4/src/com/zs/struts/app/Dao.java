package com.zs.struts.app;

import java.util.ArrayList;
import java.util.List;

public class Dao {
	
	private List<Department> departments = new ArrayList<>();
	private List<Role> roles = new ArrayList<>();
	
	public List<Department> getDepartment(){
		departments.add(new Department(1001, "科技部"));
		departments.add(new Department(1002, "事业部"));
		departments.add(new Department(1003, "营销部"));
		departments.add(new Department(1004, "IT部"));
		return departments;
	}
	
	public List<Role> getRoles(){
		roles.add(new Role(2001, "经理"));
		roles.add(new Role(2002, "科研人员"));
		roles.add(new Role(2003, "HR"));
		return roles;
	}
}
