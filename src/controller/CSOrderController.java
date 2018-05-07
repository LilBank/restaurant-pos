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
import util.ImageFactory;
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
	ImageFactory instance = ImageFactory.getInstance();

	@FXML
	public void initialize() {
		// adding buttons to foodpane
		System.out.println(tablenumber);
		// instance.getFoodButton().forEach(x -> foodpane.getChildren().add(x));
		foodpane.getChildren().addAll(instance.getFoodButton());
		instance.getDrinkButton().forEach(x -> drinkpane.getChildren().add(x));
		//
//		setFoodImage(foodname, foodUrl);
//		setDrinkImage(drinkname, drinkUrl);
	}
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
	//				o.addOrder((Menu) button.getUserData());
					System.out.println(((Menu) button.getUserData()).getName());

				}
			});
			foodpane.getChildren().add(button);
		}
	}

	// public void setFoodImage(List<Menu> items, List<String> url) {
	// int i = 0;
	// for (Menu item : items) {
	// Button button = new Button(item.getName());
	// // Image image = new Image(url.get(0));
	// // i++;
	// // ImageView view = new ImageView(image);
	// // view.setFitHeight(100);
	// // view.setFitWidth(100);
	// button.setPrefSize(150, 150);
	// button.setWrapText(true);
	// button.setTextAlignment(TextAlignment.CENTER);
	// // button.setGraphic(view);
	// button.setUserData(item);
	//
	// // set handler for the button
	// button.setOnMouseClicked(new EventHandler<MouseEvent>() {
	// @Override
	// public void handle(MouseEvent event) {
	// o.addOrder((Menu) button.getUserData());
	// System.out.println(((Menu) button.getUserData()).getName());
	//
	// }
	// });
	// foodpane.getChildren().add(button);
	// }
	// }
	//
	// public void setDrinkImage(List<Menu> items, List<String> url) {
	// int i = 0;
	// for (Menu item : items) {
	// Button button = new Button(item.getName());
	// // Image image = new Image(url.get(0));
	// // i++;
	// // ImageView view = new ImageView(image);
	// // view.setFitHeight(100);
	// // view.setFitWidth(100);
	// button.setPrefSize(150, 150);
	// button.setWrapText(true);
	// button.setTextAlignment(TextAlignment.CENTER);
	// // button.setGraphic(view);
	// button.setUserData(item);
	//
	// // set handler for the button
	// button.setOnMouseClicked(new EventHandler<MouseEvent>() {
	// @Override
	// public void handle(MouseEvent event) {
	// o.addOrder((Menu) button.getUserData());
	// System.out.println(((Menu) button.getUserData()).getName());
	//
	// }
	// });
	// foodpane.getChildren().add(button);
	// }
	// }
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
	//				o.addOrder((Menu) button.getUserData());
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
