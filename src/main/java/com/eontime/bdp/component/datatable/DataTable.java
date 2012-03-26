/**
 * 
 */
package com.eontime.bdp.component.datatable;

/**
 * @author yqx
 *
 */
public class DataTable extends org.primefaces.component.datatable.DataTable {

	public DataTable() {
		super();
		System.out.println("-------------");
		this.setPaginator(false);
		this.setPaginatorPosition("top");
	}
	
	
}
