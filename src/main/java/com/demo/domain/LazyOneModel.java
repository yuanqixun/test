/**
 * 
 */
package com.demo.domain;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.demo.util.BDP;

/**
 * @author yqx
 * 
 */
@ConversationScoped
public class LazyOneModel extends LazyDataModel<One> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@BDP
	EntityManager em;
	
	List<One> currentList;
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.primefaces.model.LazyDataModel#load(int, int, java.lang.String,
	 * org.primefaces.model.SortOrder, java.util.Map)
	 */
	@Override
	public List<One> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
	    currentList = em.createQuery("select o from One o", One.class).setMaxResults(pageSize).setFirstResult(first).getResultList();
	    return currentList;
	}

	@Override
	public int getRowCount() {
		return 2;
//		return em.createQuery("select count(o) from One o", Long.class).getSingleResult().intValue();
	}

	@Override
	public One getRowData(String rowKey) {
		for (One one : currentList) {
			if(one.getOneId().equals(rowKey))
				return one;
		}
		return null;
	}

	@Override
	public Object getRowKey(One one) {
		return one.getOneId();
	}

}
