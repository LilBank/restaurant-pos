package controller;

import application.OrderView;
import database.DBManager;
import database.DBObserver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import application.Login;
import application.MGEditMenu;
import application.ManageUser;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.TextAlignment;
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
public class TableViewController implements Observer {
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
	private Button manageTable;
	@FXML
	private Button endday;
	@FXML
	private FlowPane buttonPane;
	@FXML
	Alert alert;

	private static UserManager um = UserManager.getInstance();
	private static DBManager dbm = DBManager.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static Timer timer = new Timer();
	private static DBObserver dbo = DBObserver.getInstance();
	private boolean admin = um.isAdmin();

	static {
		System.out.println("test static");
		dbo.setAllRows();
		dbo.setChanges();
		runTask();
	}

	public void initialize() {
		if (!admin) {
			editMenu.setDisable(true);
			editMenu.setVisible(false);
			manageUser.setDisable(true);
			manageUser.setVisible(false);
			endday.setDisable(true);
			endday.setVisible(false);
		}
		createButton();
		dbo.addObserver(this);
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
		button.setUnderline(false);
		ScreenController.switchWindow((Stage) button.getScene().getWindow(),
				new OrderView(button.getText(), foodname, drinkname));
	}

	/**
	 * Handler for Edit image button. When event receive the Edit menu scene is
	 * shown.
	 * 
	 * @param event
	 */
	public void editMenuButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) editMenu.getScene().getWindow(), new MGEditMenu());
	}

	/**
	 * Handler for Manage user button. When event receive the Manage menu scene
	 * is shown.
	 * 
	 * @param event
	 */
	public void manageUserButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) manageUser.getScene().getWindow(), new ManageUser());
	}

	/**
	 * Handler for logout button. When event receive the login scene is shown.
	 * 
	 * @param event
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

	/**
	 * Handler for summary button. When event receive summary dialod is shown.
	 * 
	 * @param event
	 */
	public void sumButtonHandler(MouseEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("Summary");
		String text = Order.getInstance().orderToText(dbm.getDBOrders("Summary"));
		alert.setContentText(text);
		alert.show();

	}

	/**
	 * Handler for Manage Table button. When event receive managing table dialog
	 * is shown.
	 * 
	 * @param event
	 */
	public void manageTableButtonHandler(MouseEvent event) {
		String[] choices = { "Add table", "Remove table" };
		List<String> temp = new ArrayList<>(Arrays.asList(choices));
		ChoiceDialog<String> dialog = new ChoiceDialog<>("SELECT", temp);
		dialog.setTitle("Manage Table");
		dialog.setHeaderText("Please select an operation");
		dialog.setContentText("operation:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			if (result.get().equals("SELECT")) {
				alert = new Alert(AlertType.ERROR, "Please select something!", ButtonType.OK);
				alert.show();
			} else if (result.get().equals(choices[0])) {
				Dialog<String> dialog2 = new TextInputDialog();
				dialog2.setHeaderText("Enter table number:");
				Optional<String> result2 = dialog2.showAndWait();
				if (result2.isPresent()) {
					try {
						int tmp = Integer.parseInt(result2.get());
						if (tmp < 8 || tmp >= 100) {
							alert = new Alert(AlertType.ERROR, "Please input numbers between 8 and 99 only!",
									ButtonType.OK);
							alert.show();
							return;
						} else if (dbm.checkTable(tmp + "")) {
							alert = new Alert(AlertType.ERROR, "Table exist already!", ButtonType.OK);
							alert.show();
							return;
						} else {
							dbm.insertTableNumber(result2.get());
							dbm.createTable(result2.get());
							createButton();
							alert = new Alert(AlertType.INFORMATION, "Table created!", ButtonType.OK);
							alert.show();
						}
					} catch (NumberFormatException ex) {
						alert = new Alert(AlertType.ERROR, "Please input numbers only!", ButtonType.OK);
						alert.show();
						return;
					}
				}
			} else if (result.get().equals(choices[1])) {
				List<String> tables = dbm.getDBTables();
				ChoiceDialog<String> dialog3 = new ChoiceDialog<>("SELECT", tables);
				dialog.setTitle("Remove Table");
				dialog.setHeaderText("Please select a table");
				dialog.setContentText("table:");
				Optional<String> result3 = dialog3.showAndWait();
				if (result3.isPresent()) {
					if (dbm.checkTableData(result3.get())) {
						dbm.deleteTable(result3.get());
						dbm.removeTableinTables(result3.get());
						createButton();
						alert = new Alert(AlertType.WARNING, "Table removed!", ButtonType.OK);
						alert.show();
					} else {
						alert = new Alert(AlertType.ERROR, "Table still contain orders!", ButtonType.OK);
						alert.show();
					}
				}
			}
		}
	}

	/**
	 * Handler for end day button. When event receive all data in summary is
	 * cleared and the program is close.
	 * 
	 * @param event
	 */
	public void endButtonHandler(MouseEvent event) {
		alert = new Alert(AlertType.WARNING,
				"Are you sure to ENDDAY, all table(s) including added table(s) ordered item(s) will be remove without paying and will not be add to summary, this operation can't be undone?",
				ButtonType.OK);
		alert.showAndWait().ifPresent(response -> {
			if (response == ButtonType.OK) {
				alert = new Alert(AlertType.WARNING, "ARE YOU SURE?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait().ifPresent(response2 -> {
					if (response2 == ButtonType.YES) {
						for (int i = 1; i <= 8; i++) {
							String table = "table" + i;
							dbm.clearTable(table);
						}
						for (int i = 0; i < buttonPane.getChildren().size(); i++) {
							Button button = (Button) buttonPane.getChildren().get(i);
							dbm.deleteTable(button.getText());
						}
						dbm.clearTable("Summary");
						dbm.clearTable("Tables");
						System.exit(0);
					}
				});
			}
		});
	}

	/*
	 * Private method for creating buttons with List<String> from database.
	 */
	private void createButton() {
		buttonPane.getChildren().clear();
		List<String> temp = dbm.getDBTables();
		for (String tablenum : temp) {
			Button button = new Button(tablenum);
			button.setWrapText(true);
			button.setPrefSize(150, 150);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					tableButtonHandler(button);
				}
			});
			buttonPane.getChildren().add(button);
		}
	}

	/**
	 * Single implementation to keep track database table changes.
	 */
	public static void runTask() {
		Runnable runTask = new Runnable() {
			public void run() {
				dbo.findChanges();
				dbo.setChangesBack();
			}
		};
		TimerTask task = new TimerTask() {
			public void run() {
				Platform.runLater(runTask);
			}
		};
		timer.schedule(task, 0, 5000);
	}

	@Override
	public void update(Observable o, Object arg) {
		List<Integer> tmp = dbo.getChanges();
		for (int i = 0; i < tmp.size(); i++) {
			if (tmp.get(i) == 1) {
				button01.setUnderline(true);
			} else if (tmp.get(i) == 2) {
				button02.setText("Income");
			} else if (tmp.get(i) == 3) {
				button03.setText("Income");
			} else if (tmp.get(i) == 4) {
				button04.setText("Income");
			} else if (tmp.get(i) == 5) {
				button05.setText("Income");
			} else if (tmp.get(i) == 6) {
				button06.setText("Income");
			} else if (tmp.get(i) == 7) {
				button07.setText("Income");
			} else if (tmp.get(i) == 8) {
				button08.setText("Income");
			}
		}
	}

}
