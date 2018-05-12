package controller;

import application.OrderView;
import database.DBManager;

import java.util.List;

import application.Login;
import application.MGEditMenu;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Menu;
import model.Order;
import util.ScreenController;
import util.UserManager;

/**
 * TableViewController(manager/employee) class contains method for handling
 * event from the UserInterface. Contains method that can look up orders in each
 * table with full permission to manage the restaurant.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class TableViewController {
	@FXML
	private Button editMenu;
	@FXML
	private Button manageUser;
	@FXML
	private Button logout;
	@FXML
	private Button button01;
	@FXML
	private Button button02;
	@FXML
	private Button button03;
	@FXML
	private Button button04;
	@FXML
	private Button button05;
	@FXML
	private Button button06;
	@FXML
	private Button button07;
	@FXML
	private Button button08;
	@FXML
	private Button summary;
	@FXML
	Alert alert;

	// for single instantiation
	private static UserManager um = UserManager.getInstance();
	private static DBManager dbm = DBManager.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");

	private boolean admin = um.isAdmin();

	@FXML
	public void initialize() {
		System.out.println("initializing");
		if (!admin) {
			editMenu.setDisable(true);
			editMenu.setVisible(false);
			manageUser.setDisable(true);
			manageUser.setVisible(false);
		}
		System.out.println("username: " + um.getUser().getUsername());
		System.out.println("access level: " + um.getUser().getAccessLevel());
		System.out.println("is admin: " + admin);
	}

	public void button01Handler(ActionEvent event) {
		tableButtonHandler(button01);
	}

	public void button02Handler(ActionEvent event) {
		tableButtonHandler(button02);
	}

	public void button03Handler(ActionEvent event) {
		tableButtonHandler(button03);
	}

	public void button04Handler(ActionEvent event) {
		tableButtonHandler(button04);
	}

	public void button05Handler(ActionEvent event) {
		tableButtonHandler(button05);
	}

	public void button06Handler(ActionEvent event) {
		tableButtonHandler(button06);
	}

	public void button07Handler(ActionEvent event) {
		tableButtonHandler(button07);
	}

	public void button08Handler(ActionEvent event) {
		tableButtonHandler(button08);
	}

	/**
	 * Handler for every table button. When event receive the MGOrder scene is
	 * shown.
	 * 
	 * @param button
	 */
	public void tableButtonHandler(Button button) {
		ScreenController.switchWindow((Stage) button.getScene().getWindow(),
				new OrderView(button.getText(), foodname, drinkname));
	}

	/**
	 * Handler for image button. When event receive the edit menu scene is
	 * shown.
	 * 
	 */
	public void editMenuButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) editMenu.getScene().getWindow(), new MGEditMenu());
	}

	/**
	 * Handler for logout button. When event receive the login scene is shown.
	 * 
	 */
	public void logoutButtonHandler(ActionEvent event) {
		alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to log out ?", ButtonType.OK);
		alert.setHeaderText("Confirmation");
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Login());
			}
		});
	}

	public void sumButtonHandler(MouseEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Summary");
		alert.setHeaderText("Information");
		String text = Order.getInstance().orderToText(DBManager.getInstance().getDBOrders("Summary"));
		alert.setContentText(text);
		alert.show();

	}
}
