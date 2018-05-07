package controller;

import java.util.List;
import java.util.Map;

import application.CSTable;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Menu;
import model.Order;
//import model.Food;
import util.ScreenController;

/**
 * CSMenuController(customer) contains method for handling all event receive
 * from the UserInterface. Contains method for customer to call for bill check
 * and order dishes.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSOrderController {
	@FXML
	private Button order;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private TextArea display;
	@FXML
	private TextField totalPrice;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;

	private static String tablenumber;
	// single instantiation
	private static DBManager dbm = DBManager.getInstance();
	private static Order o = Order.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static List<String> foodUrl = dbm.getFoodUrl("Foods");
	private static List<String> drinkUrl = dbm.getFoodUrl("Drinks");
	// private static List<String> foodname = dbm.getFoodname("Foods", "name");
	// private static List<String> drinkname = dbm.getFoodname("Drinks", "name");
	// private static List<String> foodUrl = dbm.getFoodname("Foods", "url");
	// private static List<String> drinkUrl = dbm.getFoodname("Foods", "url");

	@FXML
	public void initialize() {
		// adding buttons to foodpane
		Map<Menu, Integer> temp = o.getOrders();
		System.out.println(tablenumber);
		setFoodImage(foodname, foodUrl);
		setDrinkImage(drinkname, drinkUrl);
	}
	// System.out.println(drinkname);
	// setImage(foodpane, foodname, foodUrl);
	// setImage(drinkpane, drinkname, drinkUrl);

	public void setFoodImage(List<Menu> items, List<String> url) {
		int i = 0;
		for (Menu item : items) {
			Button button = new Button(item.getName());
			// Image image = new Image(url.get(0));
			// i++;
			// ImageView view = new ImageView(image);
			// view.setFitHeight(100);
			// view.setFitWidth(100);
			button.setPrefSize(150, 150);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			// button.setGraphic(view);
			button.setUserData(item);

			// set handler for the button
			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					o.addOrder((Menu) button.getUserData());
					System.out.println(((Menu) button.getUserData()).getName());

				}
			});
			foodpane.getChildren().add(button);
		}
	}

	public void setDrinkImage(List<Menu> items, List<String> url) {
		int i = 0;
		for (Menu item : items) {
			Button button = new Button(item.getName());
			// Image image = new Image(url.get(0));
			// i++;
			// ImageView view = new ImageView(image);
			// view.setFitHeight(100);
			// view.setFitWidth(100);
			button.setPrefSize(150, 150);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			// button.setGraphic(view);
			button.setUserData(item);

			// set handler for the button
			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					o.addOrder((Menu) button.getUserData());
					System.out.println(((Menu) button.getUserData()).getName());

				}
			});
			foodpane.getChildren().add(button);
		}
	}

	// during in test
	public void orderButtonHandler(ActionEvent event) {

	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	/**
	 * Handler for logout button. When event receive the Start up scene is shown.
	 * 
	 */
	public void exitButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) exit.getScene().getWindow(), new Main());
	}

	// during in test
	public static void setTable(String arg) {
		tablenumber = arg;
	}

	// during in test
	public int getTable() {
		return Integer.parseInt(tablenumber);
	}
}
