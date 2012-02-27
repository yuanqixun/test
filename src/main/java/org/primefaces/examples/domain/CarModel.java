package org.primefaces.examples.domain;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

public class CarModel extends ListDataModel<Car> implements SelectableDataModel<Car>{
	
	
	
	public CarModel(List<Car> arg0) {
		super(arg0);
	}

	public Car getRowData(String rowKey) {
		if (getWrappedData() == null)
			return null;
		List<Car> users = (List<Car>) getWrappedData();

		for (Car d : users) {
			if (d.getName().equals(rowKey))
				return d;
		}

		return null;
	}

	public Object getRowKey(Car d) {
		return d.getName();
	}
}
