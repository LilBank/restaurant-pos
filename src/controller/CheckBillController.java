package controller;

import application.OrderView;
import application.Tableview;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Order;
import util.ScreenController;

/**
 * CustomerCheckBillController contains method for handling all event receive
 * from the UserInterface. Contains method for calculating customer bills.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CheckBillController {
	@FXML
	private Button thousand;
	@FXML
	private Button fiveHundred;
	@FXML
	private Button oneHundred;
	@FXML
	private Button fifty;
	@FXML
	private Button twenty;
	@FXML
	private Button ten;
	@FXML
	private Button five;
	@FXML
	private Button two;
	@FXML
	private Button one;
	@FXML
	private Button back;
	@FXML
	private Button clear;
	@FXML
	private Button pay;
	@FXML
	private TextField total;
	@FXML
	private TextField customerPay;
	@FXML
	private TextField change;
	@FXML
	private Alert alert;

	// instance of classes
	private static int csBill;
	private static String tablenumber;
	private static DBManager dbm = DBManager.getInstance();
	/* the money paid by the customer */
	private int csMoney;
	private int Change;

	@FXML
	public void initialize() {
		total.setText(csBill + "");
	}

	public void thousandButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(thousand.getText()));
	}

	public void fiveHundredButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(fiveHundred.getText()));
	}

	public void oneHundredButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(oneHundred.getText()));
	}

	public void fiftyButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(fifty.getText()));
	}

	public void twentyButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(twenty.getText()));
	}

	public void tenButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(ten.getText()));
	}

	public void fiveButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(five.getText()));
	}

	public void twoButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(two.getText()));
	}

	public void oneButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(one.getText()));
	}

	/*
	 * A method for increasing the customer's money according to the button
	 * pressed.
	 * 
	 */
	private void sumMoney(int number) {
		this.csMoney += number;
		customerPay.setText(csMoney + "");
		calculateChange();
	}

	/*
	 * Method for calculating customer's change. Set text and show in the UI.
	 * 
	 */
	private void calculateChange() {
		int totalMoney = Integer.parseInt(total.getText());
		change.setText(this.csMoney - totalMoney + "");
		Change = this.csMoney - totalMoney;
	}

	/**
	 * Handler for back button. When event receive the previous table OrderView
	 * is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new OrderView(tablenumber));
	}

	/**
	 * Handler for clear button. When event receive, set the customer paid money
	 * to 0 and clear the textfield.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.csMoney = 0;
		customerPay.clear();
		change.clear();
	}

	/**
	 * Handler for pay button. When event receive and the customer pay is more
	 * than bill then table view is shown.
	 * 
	 * @param event
	 */
	public void payButtonHandler(MouseEvent event) {
		if (csBill > csMoney || csBill == 0) {
			alert = new Alert(AlertType.ERROR, "Insufficient pay! ", ButtonType.OK);
			alert.show();
		} else {
			alert = new Alert(AlertType.CONFIRMATION, "Are you sure?  ", ButtonType.OK);
			alert.showAndWait().ifPresent(response -> {
				if (response == ButtonType.OK) {
					alert = new Alert(AlertType.WARNING, "CHANGE: " + Change, ButtonType.OK);
					alert.showAndWait().ifPresent(action -> {
						if (action == ButtonType.OK) {
							dbm.insertToSum(dbm.getDBOrders(tablenumber));
							dbm.clearTable(tablenumber);
							ScreenController.switchWindow((Stage) pay.getScene().getWindow(), new Tableview());
						}
					});
				}
			});
		}
	}

	/**
	 * Static method for setting the customer bill.
	 * 
	 * @param customerBill
	 */
	public static void setBill(String customerBill) {
		csBill = Integer.parseInt(customerBill);
	}

	/**
	 * Static method for setting the customers tbale.
	 * 
	 * @param tablenumber
	 */
	public static void setTable(String tablenum) {
		tablenumber = tablenum;
	}

}
