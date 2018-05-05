package controller;

import application.CSOrder;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import util.ScreenController;

/**
 * CustomerTableController(customer) contains method for handling all event
 * receive from the UserInterface. Contains method for opening CSOrder for
 * customer to order menu.
 * 
 * @author Piyawat & Vichaphol
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
	private Button ok;
	@FXML
	private Button back;
	@FXML
	private TextField display;
	@FXML
	private Alert alert;

	private String number = "";

	/**
	 * JavaFX calls the initialize() method of your controller when it creates the
	 * UI form, after the components have been created and @FXML annotated
	 * attributes have been set. This is to limit textfield char to 2.
	 */
	@FXML
	public void initialize() {
		display.setTextFormatter(
				new TextFormatter<String>(change -> change.getControlNewText().length() <= 2 ? change : null));
	}

	public void button1Handler(ActionEvent event) {
		numberButtonHandler(button1.getText());
	}

	public void button2Handler(ActionEvent event) {
		numberButtonHandler(button2.getText());
	}

	public void button3Handler(ActionEvent event) {
		numberButtonHandler(button3.getText());
	}

	public void button4Handler(ActionEvent event) {
		numberButtonHandler(button4.getText());
	}

	public void button5Handler(ActionEvent event) {
		numberButtonHandler(button5.getText());
	}

	public void button6Handler(ActionEvent event) {
		numberButtonHandler(button6.getText());
	}

	public void button7Handler(ActionEvent event) {
		numberButtonHandler(button7.getText());
	}

	public void button8Handler(ActionEvent event) {
		numberButtonHandler(button8.getText());
	}

	public void button9Handler(ActionEvent event) {
		numberButtonHandler(button9.getText());
	}

	public void button0Handler(ActionEvent event) {
		numberButtonHandler(button0.getText());
	}

	/**
	 * Handler for back button. When event receive the Start Up scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Main());
	}

	/**
	 * Handler for every number button. When event receive, and if argument is
	 * acceptable then it is parse to String for later display.
	 * 
	 * @param event
	 */
	public void numberButtonHandler(String number) {
		try {
			this.number += number;
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		display.setText(this.number);
		clear();
	}

	/**
	 * Handler for clear button. When event receive, sets the display and the
	 * private number attribute empty.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.number = "";
		clear();
	}

	/**
	 * Handler for ok button. When event receive, if the number input is two-digits
	 * and not more than maximum limit then the next Scene is activated.
	 * 
	 * @param event
	 */
	public void okButtonHandler(ActionEvent event) {
		if (number.length() == 0) {
			alert = new Alert(AlertType.ERROR, "Please input table number.", ButtonType.OK);
			alert.show();
		} else {
			ScreenController.switchWindow((Stage) ok.getScene().getWindow(), new CSOrder(number));
		}
	}

	/**
	 * Set the number (private attribute) to 0.
	 */
	public void clear() {
		display.setText(number + "");
	}

}
