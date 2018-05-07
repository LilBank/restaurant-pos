package controller;

import application.OrderView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
	private TextField total;
	@FXML
	private TextField customerPay;
	@FXML
	private TextField change;

	/* the money paid by the customer */
	private int csMoney;

	private static int csBill;

	private static String tablenumber;

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

	// during in test
	public static void setBill(String arg) {
		csBill = Integer.parseInt(arg);
	}

	// during in test
	public static void setTable(String arg) {
		tablenumber = arg;
	}

	/**
	 * Handler for back button. When event receive the previous table OrderView
	 * is shwon.
	 * 
	 * @param event
	 */
	public void backButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new OrderView(tablenumber));
	}

}
