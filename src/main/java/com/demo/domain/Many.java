package com.demo.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_MANY")
public class Many implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String manyId;

	@Column(name = "DESCRIPTION")
	private String description;

	/*
	 * 设置对应数据表的列名和引用的数据表的列名
	 * 设置在“一方”pojo的外键字段上,用mappedBy标识关联
	 * cascade设置-如果设置为all，则删除子表会导致删除主表
	 */
	@JoinColumn(name = "ONE_ID", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST,
			CascadeType.REFRESH })
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
