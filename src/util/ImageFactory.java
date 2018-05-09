package util;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;
import model.Menu;
import model.Order;

public class ImageFactory {
	/** singleton instance of ImageFactory. */
	protected static ImageFactory factory;
	private static DBManager dbm = DBManager.getInstance();
	private static Order o = Order.getInstance();

	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static List<String> foodUrl = dbm.getFoodUrl("Foods");
	private static List<String> drinkUrl = dbm.getFoodUrl("Drinks");
	private static List<Button> foodButtonList = new ArrayList<Button>();
	private static List<Button> drinkButtonList = new ArrayList<Button>();

	/**
	 * Get an instance of ImageFactory.
	 * 
	 * @return object of a subclass
	 */
	public static ImageFactory getInstance() {
		if (factory == null)
			factory = new ImageFactory();
		return factory;
	}

	public ImageView getImage(String filename) {
		Image image = new Image(filename);
		return new ImageView(image);
	}

	public static List<Button> getFoodButton() {
		return foodButtonList;
	}

	public static List<Button> getDrinkButton() {
		return drinkButtonList;
	}

	public static void loadFoodImage() {
		if (foodButtonList.size() == 0) {
			int i = 0;
			for (Menu item : foodname) {
				Button button = new Button(item.getName());
				Image image = new Image(foodUrl.get(i), true);
				i++;
				ImageView view = new ImageView(image);
				view.setFitHeight(100);
				view.setFitWidth(100);
				button.setPrefSize(150, 150);
				button.setWrapText(true);
				button.setTextAlignment(TextAlignment.CENTER);
				button.setGraphic(view);
				button.setUserData(item);

				// set handler for the button
				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						o.addOrder((Menu) button.getUserData());
						System.out.println(((Menu) button.getUserData()).getName());

					}
				});
				foodButtonList.add(button);
				System.out.println("Done Loading");

			}
		}

	}

	public static void loadDrinkImage() {
		if (drinkButtonList.size() == 0) {
			int i = 0;
			for (Menu item : drinkname) {
				Button button = new Button(item.getName());
				Image image = new Image(drinkUrl.get(i), true);
				i++;
				ImageView view = new ImageView(image);
				view.setFitHeight(100);
				view.setFitWidth(100);
				button.setPrefSize(150, 150);
				button.setWrapText(true);
				button.setTextAlignment(TextAlignment.CENTER);
				button.setGraphic(view);
				button.setUserData(item);

				// set handler for the button
				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						o.addOrder((Menu) button.getUserData());
						System.out.println(((Menu) button.getUserData()).getName());

					}
				});
				drinkButtonList.add(button);
				System.out.println("Done Loading");
			}

		}
	}
}
