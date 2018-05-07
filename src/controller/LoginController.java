package controller;

import application.TableView;
import application.Main;
import database.DBManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.PrivilegeEnum;
import model.User;
import util.ScreenController;
import util.UserManager;

/**
 * LoginController contains method for handling all event receive from the
 * UserInterface.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class LoginController {
	@FXML
	private Button cancel;
	@FXML
	private Button login;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Alert alert;
	
	//single instantiation
	private static DBManager dbm = DBManager.getInstance();

	/**
	 * Method for handling cancel button. When event receive Main scene is
	 * shown.
	 * 
	 * @param event
	 */
	public void cancelButtonHandler(MouseEvent event) {
		ScreenController.switchWindow((Stage) cancel.getScene().getWindow(), new Main());
	}

	/**
	 * Method for handling login button. When event receive the implementation
	 * below is done. Login when the username and password match .
	 * 
	 * @param event
	 */
	public void loginButtonHandler(ActionEvent event) {
		// empty username textfield
		if (username.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Username is empty.", ButtonType.OK);
			alert.setHeaderText("Inputfield Error");
			alert.show();
		}
		// either password field is empty
		else if (password.getText().equals("")) {
			alert = new Alert(AlertType.ERROR, "Password is empty!", ButtonType.OK);
			alert.setHeaderText("Inputfield Error");
			alert.show();
		}
		// check if this user is valid in which access level
		else {
			int accessLevel = dbm.login(username.getText(), password.getText());
			// int = 2 for manager mode
			if (accessLevel == 2) {
				alert = new Alert(AlertType.NONE, "Welcome manager: " + username.getText(), ButtonType.OK);
				alert.setHeaderText("Login Success");
				alert.show();
				UserManager.getInstance().setUser(new User(username.getText(), PrivilegeEnum.ADMIN));
				ScreenController.switchWindow((Stage) login.getScene().getWindow(), new TableView());
			}
			// int = 1 for normal mode
			if (accessLevel == 1) {
				alert = new Alert(AlertType.NONE, "Welcome waiter: " + username.getText(), ButtonType.OK);
				alert.setHeaderText("Login Success");
				alert.show();
				UserManager.getInstance().setUser(new User(username.getText(), PrivilegeEnum.USER));
				ScreenController.switchWindow((Stage) login.getScene().getWindow(), new TableView());
			}
			// wrong password
			if (accessLevel == 0) {
				alert = new Alert(AlertType.ERROR, "Wrong password!", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			}
			// User does not exists
			if (accessLevel == -1) {
				alert = new Alert(AlertType.ERROR, "User does not exist!", ButtonType.OK);
				alert.setHeaderText("Inputfield Error");
				alert.show();
			}
		}
	}
}
