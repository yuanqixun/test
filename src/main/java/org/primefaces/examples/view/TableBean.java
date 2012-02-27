/**
 * 
 */
package org.primefaces.examples.view;

/**
 * @author yqx
 *
 */

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.faces.model.ListDataModel;
import javax.inject.Named;

import org.primefaces.examples.domain.Car;
import org.primefaces.examples.domain.CarModel;

@Named
@Stateful
public class TableBean {
	private final static String[] colors;

	private final static String[] manufacturers;

	private List<Car> carsSmall;
	private ListDataModel<Car> carListModel;
	private CarModel selectedCar;
	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";

		manufacturers = new String[10];
		manufacturers[0] = "Mercedes";
		manufacturers[1] = "BMW";
		manufacturers[2] = "Volvo";
		manufacturers[3] = "Audi";
		manufacturers[4] = "Renault";
		manufacturers[5] = "Opel";
		manufacturers[6] = "Volkswagen";
		manufacturers[7] = "Chrysler";
		manufacturers[8] = "Ferrari";
		manufacturers[9] = "Ford";
	}

	public TableBean() {
		carsSmall = new ArrayList<Car>();

		populateRandomCars(carsSmall, 9);
	}

	@PostConstruct
	public void afterCreate() {
		carListModel = new CarModel(carsSmall);
	}

	private void populateRandomCars(List<Car> list, int size) {
		for (int i = 0; i < size; i++)
			list.add(new Car("car:" + i));
	}

	public CarModel getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(CarModel selectedCar) {
		this.selectedCar = selectedCar;
	}

	public List<Car> getCarsSmall() {
		return this.carsSmall;
	}

	public ListDataModel<Car> getCarListModel() {
		return carListModel;
	}
}
