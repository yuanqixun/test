package com.demo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_MANY")
public class Many implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MANY_ID", nullable = false)
	private String manyId;
	@Column(name = "DESCRIPTION")
	private String description;

	@JoinColumn(name = "ONE_ID", referencedColumnName = "ONE_ID")
	// 设置对应数据表的列名和引用的数据表的列名
	@ManyToOne
	// 设置在“一方”pojo的外键字段上
	private One oneId;

	public String getManyId() {
		return manyId;
	}

	public void setManyId(String manyId) {
		this.manyId = manyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public One getOneId() {
		return oneId;
	}

	public void setOneId(One oneId) {
		this.oneId = oneId;
	}

}
