/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author yqx
 * 
 */
@Entity
@Table(name = "T_MANYA")
public class ManyA implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "MANYA_ID", nullable = false)
	private String manyaId;
	@Column(name = "DESCRIPTION")
	private String description;
	@ManyToMany
	@JoinTable(name = "TMANY1_TMANY2", joinColumns = { @JoinColumn(name = "MANYA_ID", referencedColumnName = "MANYA_ID") }, inverseJoinColumns = { @JoinColumn(name = "MANYB_ID", referencedColumnName = "MANYB_ID") })
	private Collection<ManyB> manybIdCollection;

	public String getManyaId() {
		return manyaId;
	}

	public void setManyaId(String manyaId) {
		this.manyaId = manyaId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ManyB> getManybIdCollection() {
		return manybIdCollection;
	}

	public void setManybIdCollection(Collection<ManyB> manybIdCollection) {
		this.manybIdCollection = manybIdCollection;
	}

}
