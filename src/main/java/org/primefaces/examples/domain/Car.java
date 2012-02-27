package org.primefaces.examples.domain;

import java.util.Date;

public class Car{
	
	private String name;
	private String desc;
	private String createdBy;
	private Date createdAt;
	
	public Car(String name) {
		this.name = name;
		this.desc = name+"'s desc";
		this.createdBy="yuan";
		this.createdAt=new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}
