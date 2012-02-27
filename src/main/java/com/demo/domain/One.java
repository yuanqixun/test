/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String oneId;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "oneId")
	// 指向多的那方的pojo的关联外键字段
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
