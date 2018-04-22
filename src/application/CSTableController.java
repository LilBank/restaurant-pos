package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * CustomerTableController contains method for handling all event recieve from
 * the UserInterface.
 * 
 * @author Piyawat & Vichapol
 *
 */
public class CSTableController {
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private Button button3;
	@FXML
	private Button button4;
	@FXML
	private Button button5;
	@FXML
	private Button button6;
	@FXML
	private Button button7;
	@FXML
	private Button button8;
	@FXML
	private Button button9;
	@FXML
	private Button button0;
	@FXML
	private Button clear;
	@FXML
	private TextField display;

	private int number = 0;

	public void button1Handler(ActionEvent event) {
		numberButtonHandler(button1.getText());
	}

	/**
	 * Handler for every number button. If argument is acceptable the it is
	 * parse to String for later display.
	 * 
	 * @param event
	 */
	public void numberButtonHandler(String number) {
		int input = 0;
		try {
			input = Integer.parseInt(number);
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}
		this.number += input;
		clear();
	}

	/**
	 * Handler for clear button. Sets the display and private number attribute
	 * empty.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.number = 0;
		clear();
	}

	/**
	 * Set the number (private attribute) to 0.
	 */
	public void clear() {
		display.setText(number + "");
	}

}
