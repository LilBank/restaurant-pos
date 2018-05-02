package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
	/** TextField of Total money */
	@FXML
	private TextField total;
	/** TextField of money paid by customer */
	@FXML
	private TextField customerPay;
	/** TextField of customer's change */
	@FXML
	private TextField change;

	/** the money paid by the customer */
	private int csMoney;

	private static int csBill;

	@FXML
	public void initialize() {
		total.setText("");
	}

	public void thousandButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(thousand.getText()));
		calculateChange();
	}

	public void fiveHundredButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(fiveHundred.getText()));
		calculateChange();
	}

	public void oneHundredButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(oneHundred.getText()));
		calculateChange();
	}

	public void fiftyButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(fifty.getText()));
		calculateChange();
	}

	public void twentyButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(twenty.getText()));
		calculateChange();
	}

	public void tenButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(ten.getText()));
		calculateChange();
	}

	public void fiveButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(five.getText()));
		calculateChange();
	}

	public void twoButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(two.getText()));
		calculateChange();
	}

	public void oneButtonHandler(ActionEvent event) {
		sumMoney(Integer.parseInt(one.getText()));
		calculateChange();
	}

	/**
	 * A method for increasing the customer's money according to the button
	 * pressed.
	 * 
	 * @param number
	 *            to sum
	 */
	public void sumMoney(int number) {
		this.csMoney += number;
		customerPay.setText(csMoney + "");
	}

	/**
	 * Method for calculating customer's change. Set text and show in the UI.
	 * 
	 */
	public void calculateChange() {
		int totalMoney = Integer.parseInt(total.getText());
		change.setText(totalMoney - this.csMoney + "");
	}

	/**
	 * Set the customer paid money to 0 and clear the textfield.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.csMoney = 0;
		customerPay.clear();
	}
	
	//during in test
	public static void setBill(String arg) {
		csBill = Integer.parseInt(arg);
	}
	
}
