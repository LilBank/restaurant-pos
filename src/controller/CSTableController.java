package controller;

import application.MGMenu;
import application.CSTable;
import application.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * CustomerTableController contains method for handling all event receive from
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
	private Button ok;
	@FXML
	private TextField display;
	@FXML
	private Button back;

	private String number = "";

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
		ScreenController.switchWindow((Stage) ok.getScene().getWindow(), new Main());
	}

	/**
	 * Handler for every number button. When event receive, and if argument is
	 * acceptable the it is parse to String for later display.
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
	 * Handler for clear button. When event receive, sets the display and
	 * private number attribute empty.
	 * 
	 * @param event
	 */
	public void clearButtonHandler(ActionEvent event) {
		this.number = "";
		clear();
	}

	/**
	 * Handler for ok button. When event receive, if the number input is
	 * two-digits and not more than maximum limit then the next Scene is
	 * activated.
	 * 
	 * @param event
	 */
	public void okButtonHandler(ActionEvent event) {
		//ScreenController.switchWindow((Stage) ok.getScene().getWindow(), new MGMenu());
	}

	/**
	 * Set the number (private attribute) to 0.
	 */
	public void clear() {
		display.setText(number + "");
	}

}
