package com.zs.hibernate.helloworld;

import java.sql.Date;

public class News {
	private Integer id;
	private String author;
	private String title;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public News(String author, String title, Date date) {
		super();
		this.author = author;
		this.title = title;
		this.date = date;
	}
	public News() {

	}
	@Override
	public String toString() {
		return "News [id=" + id + ", author=" + author + ", title=" + title + ", date=" + date + "]";
	}
	
}
