package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * during in test for the table
 * 
 * @author testing
 *
 */
public class Food {
	private final SimpleStringProperty name;
	private final SimpleIntegerProperty quantity;
	private final SimpleIntegerProperty price;

	public Food(String name, int quantity, int price) {
		this.name = new SimpleStringProperty(name);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.price = new SimpleIntegerProperty(price);
	}

	public String getName() {
		return name.get();
	}

	public void setFirstName(String Name) {
		name.set(Name);
	}

	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(int number) {
		quantity.set(number);
	}

	public int getPrice() {
		return price.get();
	}

	public void setPrice(int number) {
		price.set(number);
	}

}
