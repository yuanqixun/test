/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author yqx
 *
 */
@Entity
@Table(name = "T_TREE")
public class Tree implements Serializable{
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE})
	@JoinColumn(name="P_ID",referencedColumnName="ID",nullable=true)
	private Tree parent;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="parent")
	private Set<Tree> children;

	public String getId() {
		return id;
	}

	public Set<Tree> getChildren() {
		return children;
	}

	public void setChildren(Set<Tree> children) {
		this.children = children;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tree getParent() {
		return parent;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}
	
	
}
