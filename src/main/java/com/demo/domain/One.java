/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yqx
 * 
 */
@Entity
@Table(name = "T_ONE")
public class One implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ONE_ID", nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String oneId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	/*
	 * 1.指向Many的那方的pojo的关联外键字段
	 * 2.cascade类型设置，是指当某个级联操作（保存、删除等）当前对象One时，是否会级联操作Many对象？
	 * 3.fetch类型设置，LAZY-当加载当前对象时，不马上加载该Many对象，EGAR-当加载当前对象时，马上加载该Many对象
	 */
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "oneId")
	private Collection<Many> manyCollection;

	public String getOneId() {
		return oneId;
	}

	public void setOneId(String oneId) {
		this.oneId = oneId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Many> getManyCollection() {
		return manyCollection;
	}

	public void setManyCollection(Collection<Many> manyCollection) {
		this.manyCollection = manyCollection;
	}

}
