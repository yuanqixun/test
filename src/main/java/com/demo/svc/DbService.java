/**
 * 
 */
package com.demo.svc;

import java.io.Serializable;

import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.demo.domain.LazyOneModel;
import com.demo.domain.One;

/**
 * @author yqx
 *
 */
@ConversationScoped
@Stateful
@Named
public class DbService implements Serializable {
	
	One selectedOne;
	
	@Inject
	LazyOneModel dataModel;

	public One getSelectedOne() {
		return selectedOne;
	}

	public void setSelectedOne(One selectedOne) {
		this.selectedOne = selectedOne;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LazyOneModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(LazyOneModel dataModel) {
		this.dataModel = dataModel;
	}
	
}
