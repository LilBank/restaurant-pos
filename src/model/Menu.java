package model;

import java.util.Objects;

/**
 * An object that represents a Menu, consist of name and price attribute. Also
 * contain method for accessing private attributes.
 * 
 * @author Piyawat & Vichaphol
 *
 */

public class Menu implements Comparable<Menu>{

	private String name;
	private int price;

	public Menu(String name, int price) {
		this.name = name;
		this.price = price;
	}

	/**
	 * Get Menu name.
	 * 
	 * @return menu name as String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get Menu price.
	 * 
	 * @return menu price as int
	 */
	public int getPrice() {
		return this.price;
	}

	/**
	 * Equal method for Menu Class. Override the method for containsKey to work
	 * with Menu.
	 *
	 * @return true if name matches
	 */
	@Override
	public boolean equals(Object obj) {
		//if both are null return true
		if (obj == this)
			return true;
		//similar to obj.getClass() != this.getClass()
		if (!(obj instanceof Menu)) {
			return false;
		}
		Menu o = (Menu) obj;
		// similar to this.name.equals(o.getName())
		return Objects.equals(this.name, o.getName());
	}

	/**
	 * Overridden method for containsKey to work with Menu.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.price);
	}
	
	//under constrution
	@Override
	public int compareTo(Menu o) {
		return 0;
	}

}
