/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author yqx
 * 
 */
@Entity
@Table(name = "T_MANYB")
public class ManyB implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MANYB_ID", nullable = false)
	private String manybId;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToMany(mappedBy = "manybIdCollection")
	private Collection<ManyA> manyaIdCollection;

	public String getManybId() {
		return manybId;
	}

	public void setManybId(String manybId) {
		this.manybId = manybId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ManyA> getManyaIdCollection() {
		return manyaIdCollection;
	}

	public void setManyaIdCollection(Collection<ManyA> manyaIdCollection) {
		this.manyaIdCollection = manyaIdCollection;
	}

}
