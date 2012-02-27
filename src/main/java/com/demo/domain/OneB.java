package com.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ONEB")
public class OneB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ONEA_ID", nullable = false)
	private String oneaId;
	@Column(name = "DESCRIPTION")
	private String description;
	@JoinColumn(name = "ONEA_ID", referencedColumnName = "ONEA_ID", insertable = false, updatable = false)
	// 设置从方指向主方的关联外键，这个ONEA_ID其实是表T_ONEA的主键
	@OneToOne
	private OneA oneA;

	public String getOneaId() {
		return oneaId;
	}

	public void setOneaId(String oneaId) {
		this.oneaId = oneaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public OneA getOneA() {
		return oneA;
	}

	public void setOneA(OneA oneA) {
		this.oneA = oneA;
	}

}