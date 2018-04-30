package model;

/**
 * An object that represents a Menu, consist of name and price attribute. Also
 * contain method for accessing private attributes.
 * 
 * @author Piyawat & Vichaphol
 *
 */

public class Menu {

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

//	/**
//	 * Equal method for Menu Class.
//	 * 
//	 * @return return true if object's name match this.name
//	 */
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null)
//			return false;
//		if (obj.getClass() != this.getClass())
//			return false;
//		return ((Menu) obj).getName().equals(this.getName());
//	}

}
