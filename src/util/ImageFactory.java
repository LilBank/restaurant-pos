package util;

import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

	private static List<Menu> foodname;
	private static List<Menu> drinkname;
	private static List<String> foodUrl;
	private static List<String> drinkUrl;
	private static List<Button> foodButtonList;
	private static List<Button> drinkButtonList;
	private Button selectedButton = null;

	private ImageFactory() {
		foodname = dbm.getFoodname("Foods");
		drinkname = dbm.getFoodname("Drinks");
		foodUrl = dbm.getFoodUrl("Foods");
		drinkUrl = dbm.getFoodUrl("Drinks");
		foodButtonList = new ArrayList<Button>();
		drinkButtonList = new ArrayList<Button>();
	}

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

	public Button getSelectedButton() {
		return selectedButton;
	}
	public void setSelectedButton(Button button) {
		this.selectedButton = button;
	}

	public List<Button> getFoodButton() {
		return foodButtonList;
	}

	public List<Button> getDrinkButton() {
		return drinkButtonList;
	}

	public void loadFoodImage() {
		if (foodButtonList.size() == 0) {
			int i = 0;
			for (Menu item : foodname) {
				Button button = new Button(item.getName());
				Image image = new Image(foodUrl.get(i), true);
				i++;
				ImageView view = new ImageView(image);
				view.setFitHeight(160);
				view.setFitWidth(130);
				button.setPrefSize(220, 220);
				button.setWrapText(true);
				button.setGraphic(view);
				button.setUserData(item);

				// set handler for the button
				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						o.addOrder((Menu) button.getUserData());
						System.out.println(((Menu) button.getUserData()).getName());
						selectedButton = button;

					}
				});
				foodButtonList.add(button);

			}
			System.out.println("Foods loaded");
		}

	}

	public void loadDrinkImage() {
		if (drinkButtonList.size() == 0) {
			int i = 0;
			for (Menu item : drinkname) {
				Button button = new Button(item.getName());
				Image image = new Image(drinkUrl.get(i), true);
				i++;
				ImageView view = new ImageView(image);
				view.setFitHeight(160);
				view.setFitWidth(130);
				button.setPrefSize(220, 220);
				button.setWrapText(true);
				button.setGraphic(view);
				button.setUserData(item);

				// set handler for the button
				button.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						o.addOrder((Menu) button.getUserData());
						System.out.println(((Menu) button.getUserData()).getName());
						selectedButton = button;
					}
				});
				drinkButtonList.add(button);
			}

			System.out.println("Drinks Loaded");
		}
	}
}
