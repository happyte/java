package com.zs.mybatis.entities;

import java.util.List;

public class Class {
	private int id;
	private String name;
	private Teacher teacher;
	List<Children> childrens;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public List<Children> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Children> childrens) {
		this.childrens = childrens;
	}
	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + ", teacher=" + teacher + ", childrens=" + childrens + "]";
	}
	
}
