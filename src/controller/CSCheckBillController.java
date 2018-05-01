package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jdk.management.resource.internal.TotalResourceContext;

/**
 * CustomerCheckBillController contains method for handling all event receive
 * from the UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class CSCheckBillController {
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
	private TextField total;
	@FXML
	private TextField customerPay;
	@FXML
	private TextField change;

	/** the money paid by the customer */
	private int CSmoney;

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

	/**
	 * A method for increasing the money when the button is pressed.
	 * 
	 * @param number
	 *            to sum
	 */
	public void sumMoney(int number) {
		this.CSmoney += number;
		customerPay.setText(CSmoney + "");
	}
	/**
	 * 
	 */
	public void calculateChange() {
		
	}
	/**
	 * Set the customer paid money to 0 and clear the textfield.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.CSmoney = 0;
		customerPay.clear();
	}
}
