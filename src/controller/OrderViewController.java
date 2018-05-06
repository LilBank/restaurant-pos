package controller;

import java.util.List;

import application.TableView;
import application.CheckBill;
import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Menu;
import util.ScreenController;
import util.UserManager;

/**
 * OrderController(manager/employee) contains method for handling all event
 * receive from the UserInterface. Contains method for viewing and ordering
 * customer orders. (With help form TA for spacing nodes)
 * 
 * @author Piyawat & Vichaphol & P'Jacky
 *
 */
public class OrderViewController {
	@FXML
	private Button order;
	@FXML
	private Button remove;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private Button checkBill;
	@FXML
	private TextField totalPrice;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;

	private static String tablenumber;

	// for single instantiation
	private static List<Menu> foods;
	private static List<Menu> drinks;
	private static UserManager um = UserManager.getInstance();

	private boolean admin = um.isAdmin();

	@FXML
	public void initialize() {
		if (!admin) {
			remove.setDisable(true);
			remove.setVisible(false);
		}

		// adding buttons to foodpane
		System.out.println(tablenumber);
		for (Menu foodname : foods) {
			Button button = new Button(foodname.getName());
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);

			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

				}
			});

			foodpane.getChildren().add(button);
		}
		for (Menu drinkname : drinks) {
			Button button = new Button(drinkname.getName());
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);

			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {

				}
			});

			drinkpane.getChildren().add(button);
		}
	}

	/**
	 * Handler for every menu button. When event receive the menu is added to
	 * the Map<Menu,QTY>.
	 * 
	 * @param event
	 */
	public void menuButtonHandler(MouseEvent event) {

	}

	/**
	 * Handler for order button. When event receive orders are sent out to the
	 * database.
	 * 
	 * @param event
	 */
	public void orderButtonHandler(MouseEvent event) {

	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new TableView());
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void checkBillButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CheckBill(null));
	}

	/**
	 * Handler for logout button. When event receive the Start up scene is
	 * shown.
	 * 
	 */
	public void exitButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) exit.getScene().getWindow(), new Main());
	}

	/**
	 * Static method for scene before opening this scene to get the button text
	 * and set as table number.
	 * 
	 * @param buttonText
	 */
	public static void setTable(String buttonText) {
		tablenumber = buttonText;
	}

	/**
	 * Static method for scene before opening this scene to get list of menu
	 * names and set the List<Menu> attribute above.
	 * 
	 * @param List
	 *            of menu names List<Menu>
	 */
	public static void setMenu(List<Menu> arg, List<Menu> arg2) {
		foods = arg;
		drinks = arg2;
	}

	// during in test
	public int getTable() {
		return Integer.parseInt(tablenumber);
	}

}
