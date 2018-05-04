package controller;

import java.util.List;

import application.CSTable;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
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
	private TextField totalPrice;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;

	private static String tablenumber;
	private static List<String> foodname = DBManager.getFoodname("Foods");
	private static List<String> drinkname = DBManager.getFoodname("Drinks");
	private static List<String> urlImage = DBManager.getImageURL("Images");

	@FXML
	public void initialize() {
		// adding buttons to foodpane
		System.out.println(tablenumber);
		setImage(foodname);
		setImage(drinkname);
	}

	public void setImage(List<String> name) {
		int i = 0;
		for (String text : foodname) {
			Button button = new Button(text);
			Image image = new Image(urlImage.get(i));
			i++;
			ImageView view = new ImageView(image);
			view.setFitHeight(100);
			view.setFitWidth(100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setGraphic(view);
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
