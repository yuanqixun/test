/**
 * 
 */
package com.demo.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author yqx
 * 
 */
@Entity
@Table(name = "T_ONEA")
public class OneA implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ONEA_ID", nullable = false)
	private String oneaId;
	@Column(name = "DESCRIPTION")
	private String description;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "oneA")
	// 主Pojo这方的设置比较简单，只要设置好级联和映射到从Pojo的外键就可以了。
	private OneB oneB;

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

	public OneB getOneB() {
		return oneB;
	}

	public void setOneB(OneB oneB) {
		this.oneB = oneB;
	}

}
