package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Optional;

import application.CheckBill;
import application.Main;
import application.Tableview;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Menu;
import model.Order;
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
public class OrderViewController implements java.util.Observer {
	@FXML
	private Button order;
	@FXML
	private Button clear;
	@FXML
	private Button remove;
	@FXML
	private Button back;
	@FXML
	private Button exit;
	@FXML
	private Button checkBill;
	@FXML
	private TextField total;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;
	@FXML
	private TextArea display;
	@FXML
	private TextArea display2;
	@FXML
	private Alert alert;

	private static String tablenumber;

	// for single instantiation
	private static List<Menu> foods;
	private static List<Menu> drinks;
	// instance of classes
	private static UserManager um = UserManager.getInstance();
	private static Order o = Order.getInstance();
	private static DBManager dbm = DBManager.getInstance();

	private boolean admin = um.isAdmin();
	private int tmpTotal;

	@FXML
	public void initialize() {
		// if not admin then remove button is disabled
		if (!admin) {
			remove.setDisable(true);
			remove.setVisible(false);
		}
		o.addObserver(this);
		// adding buttons to each pane
		setButtons(foods, foodpane);
		setButtons(drinks, drinkpane);
		setDisplayProp();
	}

	/**
	 * Overridden method from java.util.Observer to set the display everytime a
	 * menu button is pressed.
	 */
	@Override
	public void update(Observable observable, Object arg) {
		setDisplay();
	}

	/**
	 * Private method for the controller to create and add buttons to the
	 * container.
	 * 
	 * @param List<Menu>
	 *            any menu list
	 */
	private void setButtons(List<Menu> items, FlowPane pane) {
		for (Menu item : items) {
			Button button = new Button(item.getName());
			// setting button properties
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setUserData(item);
			// set handler for the button
			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					o.addOrder((Menu) button.getUserData());
				}
			});
			pane.getChildren().add(button);
		}
	}

	/**
	 * Handler for order button. When event receive orders are sent out to the
	 * database.
	 * 
	 * @param event
	 */
	public void orderButtonHandler(MouseEvent event) {
		// if order list is empty
		if (o.getOrders().isEmpty()) {
			alert = new Alert(AlertType.ERROR, "Must order atleast one item!", ButtonType.OK);
			alert.show();
		}
		// order confirmation
		else {
			alert = new Alert(AlertType.CONFIRMATION, "Are you sure to order?", ButtonType.YES, ButtonType.NO);
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.YES) {
					dbm.orderToDB(tablenumber, o.getOrders());
					setDisplay2();
					o.clearOrders();
				}
			});
		}
	}

	/**
	 * Handler for clear button. When event receive current orders from
	 * Map<Menu,QTY> is removed.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(MouseEvent event) {
		o.clearOrders();
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		o.clearOrders();
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void billButtonHandler(MouseEvent event) {
		o.clearOrders();
		String total = tmpTotal + "";
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CheckBill(total, tablenumber));
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
	 * Handler for remove button. When event receive, the selected menu is
	 * removed out of list by 1.
	 * 
	 * @param event
	 */
	public void removeButtonHandler(MouseEvent event) {
		confirmRemove();
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

	// use another map
	private void confirmRemove() {
		Map<Menu, Integer> temp = o.getDBOrders(tablenumber);
		List<String> temp2 = new ArrayList<>();
		temp.forEach((k, v) -> temp2.add(k.getName()));
		ChoiceDialog<String> dialog = new ChoiceDialog<>("SELECT", temp2);
		dialog.setTitle("Remove");
		dialog.setHeaderText("Please select an order wish to remove");
		dialog.setContentText("Choose which to remove:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (result.get().equals("SELECT")) {
				alert = new Alert(AlertType.ERROR, "Please select something!", ButtonType.OK);
				alert.show();
			} else if (!dbm.checkDBFood(result.get(), tablenumber)) {
				alert = new Alert(AlertType.ERROR, "Food requested does not exist!", ButtonType.OK);
				alert.show();
			} else if (dbm.checkDBFood(result.get(), tablenumber)) {
				alert = new Alert(AlertType.INFORMATION, result.get() + " is removed by 1.", ButtonType.OK);
				alert.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK) {
						String tabletmp = "table" + tablenumber;
						Menu menu = o.getMenu(result.get());
						DBManager.getInstance().insertTo(tabletmp, menu);
					}
				});
			}
		}
		setDisplay2();
		setDisplay();
	}

	// set the current total
	private void setTotal() {
		String temp = "" + (o.getTotal() + tmpTotal);
		total.setText(temp);
	}

	/*
	 * set the temporary total attribute which is use to display the current
	 * total
	 */
	private void setTempTotal(Map<Menu, Integer> map) {
		tmpTotal = o.getTotal(map);
	}

	// set the display properties
	private void setDisplayProp() {
		display.setDisable(true);
		display.setText(tablenumber);
		display2.setDisable(true);
		setDisplay2();
		setTotal();
	}

	// set the top display in the UI
	private void setDisplay() {
		String text = o.orderToText(o.getOrders());
		display.setText(text);
		setTotal();
	}

	// set the lower display in the UI
	private void setDisplay2() {
		Map<Menu, Integer> temp = o.getDBOrders(tablenumber);
		String text = o.orderToText(temp);
		display2.setText(text);
		setTempTotal(temp);
		o.clearOrdersTmp();
	}
}
