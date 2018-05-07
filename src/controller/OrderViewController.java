package controller;

import java.util.List;
import java.util.Map;
import java.util.Observable;

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

	// under construction (testing observation)
	private static OrderViewController instance;

	private boolean admin = um.isAdmin();
	// get total from DTB
	private int tmpTotal;

	@FXML
	public void initialize() {
		// if not admin then remove button is disabled
		if (!admin) {
			remove.setDisable(true);
			remove.setVisible(false);
		}
		// testing able number
		System.out.println(tablenumber);
		// adding buttons to each pane
		setButtons(foods, foodpane);
		setButtons(drinks, drinkpane);
		setDisplayProp();
	}

	// during in test
	private void setDisplayProp() {
		display.setDisable(true);
		display.setText(tablenumber);
		display2.setDisable(true);
		// every time OrderView must show ordered items
		setDisplay2();
		setTotal();
	}

	// during in test
	// constructor clarify at OrderView for adding it into Observable list
	public OrderViewController() {
	}

	// during in test
	public static OrderViewController getInstance() {
		if (instance == null)
			instance = new OrderViewController();
		return instance;
	}

	// during in test (used in OrderTable)
	public void setDisplay(String text) {
		try {
			display.setText(text);
			System.out.println("display : " + text);
			System.out.println("display method is working");
		} catch (NullPointerException ex) {
			System.out.println("display method is not working");
			ex.printStackTrace();
		}
	}

	// during in test
	public TextArea getDisplay() {
		return this.display;
	}

	// during in test (use in this class)
	public void setDisplay() {
		String text = null;
		// test text existence
		try {
			// convert current orders to texts
			text = o.orderToText(o.getOrders());
			System.out.println("text is not null");
		} catch (NullPointerException ex) {
			System.out.println("text is null");
			ex.printStackTrace();
		}
		// test display existence
		try {
			instance.getDisplay();
			System.out.println("display is not null");
		} catch (NullPointerException ex) {
			System.out.println("display is null");
			ex.printStackTrace();
		}
		// this line below keeps null
		instance.getDisplay().setText(text);
		// for testing
		System.out.println("method update in OVC is working");
	}

	// for display to setText (temporary)
	public void setTemporary() {
		String text = o.orderToText(o.getOrders());
		display.setText(text);
	}

	// during in test (seems to work the most)
	@Override
	public void update(Observable observable, Object arg) {
		setDisplay();
		setDisplay2();
	}

	// for testing (temporary)
	public void setDisplay2() {
		// get orders from database
		Map<Menu, Integer> temp = dbm.getDBOrders(tablenumber);
		// convert all order to texts
		String text = o.orderToText(temp);
		display2.setText(text);
		// use the DBmap before clearing
		setTempTotal(temp);
		System.out.println("tmpTotal value: " + tmpTotal);
		// must clear orders otherwise DTBorders will combine with new orders
		o.clearOrders();
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
					// adding order to map
					o.addOrder((Menu) button.getUserData());
					// after add set the total textfield
					setTotal();
					// for display to setText (temporary)
					setTemporary();
					// for testing
					System.out.println(o.orderToText(o.getOrders()));
				}
			});
			// add button to the pane
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
					o.printOrders();
					// for testing
					System.out.println("Current order(s): " + o.getOrders().size());
					// get current orders
					Map<Menu, Integer> temp = o.getOrders();
					// insert order to database
					dbm.orderToDB(tablenumber, temp);
					// clear orders that are inserted to DTB (after insert must
					// clear)
					o.clearOrders();
					// during in test (will use observer pattern)
					setDisplay2();
					// all order from display move to display2
					display.setText("");
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
		//must be after clear order prevent mixing
		setTotal();
		// for testing
		System.out.println("all current orders cleared.");
		System.out.println("Map size: " + o.getOrders().size());
		// during in test (will use observer pattern)
		display.setText("");
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void billButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CheckBill(0 + ""));
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
	private void setTotal() {
		String temp = "" + (o.getTotal() + tmpTotal);
		total.setText(temp);

	}

	// during in test
	private void setTempTotal(Map<Menu, Integer> map) {
		tmpTotal = o.getTotal(map);
	}

	// during in test
	public int getTable() {
		return Integer.parseInt(tablenumber);
	}
}
