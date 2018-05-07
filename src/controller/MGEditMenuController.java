package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import application.Tableview;
import application.Main;
import database.DBManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Menu;
import sun.nio.ch.SelectionKeyImpl;
import util.DownloadTask;
import util.ScreenController;

/**
 * MGTableController(manager) class contains method for handling event from the
 * UserInterface. Contains method for adding menu with picture or removing a
 * menu.
 * 
 * @author Piyawat & Vichaphol
 *
 */
public class MGEditMenuController {
	@FXML
	Button back;
	@FXML
	Button newFood;
	@FXML
	Button newDrink;
	@FXML
	Button deleteImage;
	@FXML
	Button logout;
	@FXML
	private FlowPane foodpane;
	@FXML
	private FlowPane drinkpane;
	/** List of all images */
	public static List<Button> folderImage = new ArrayList<>();
	// single instantiation
	private static DBManager dbm = DBManager.getInstance();
	private static List<Menu> foodname = dbm.getFoodname("Foods");
	private static List<Menu> drinkname = dbm.getFoodname("Drinks");
	private static List<String> foodUrl = dbm.getFoodUrl("Foods");
	private static List<String> drinkUrl = dbm.getFoodUrl("Drinks");

	/**
	 * Bind listView with ListProperty at the beginning.
	 */
	@FXML
	public void initialize() {
//		setFoodButtons(foodname, foodpane);
//		setDrinkButtons(drinkname, drinkpane);
		

	}

	public static List<Button> getImage() {
		return folderImage;
	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertFoodHandler(ActionEvent event) {
		createData("Foods", "Taco", "80");

	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertDrinkHandler(ActionEvent event) {
		// createData("Drinks", name, price, url);

	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void createData(String table, String name, String price) {
		// Ask user to input dialog.
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Input URL");
		dialog.setHeaderText("Text Input Dialog");
		dialog.setContentText("Please Input URL:");
		// Get the response value.
		Image image = null;
		Alert alert = null;
		Optional<String> result = dialog.showAndWait();
		if (result.get().equals("")) {
			alert = new Alert(AlertType.ERROR, "Input is empty.", ButtonType.OK);
			alert.setHeaderText("Inputfield Error");
			alert.show();

		}
		if (!result.get().contains(".jpg")) {
			alert = new Alert(AlertType.ERROR, "Url is incorrect", ButtonType.OK);
			alert.show();
		} else {
			if (result.isPresent()) {
				// dbm.InsertTo(table, name, price, result.get());
				Button button = new Button();
				button.setPrefSize(100, 100);
				ImageView view = new ImageView(image);
				button.setGraphic(view);
				foodpane.getChildren().add(button);
			}
		}
	}

	/**
	 * Private method for the controller to create and add buttons to the container.
	 * 
	 * @param List<Menu>
	 *            any menu list
	 */
	private void setFoodButtons(List<Menu> items, FlowPane pane) {
		Image image = null;
		for (Menu item : items) {
			Button button = new Button(item.getName());
			image = new Image(foodUrl.get(1));
			ImageView view = new ImageView(image);
			view.setFitHeight(100);
			view.setFitWidth(100);
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setUserData(item);
			button.setGraphic(view);
			pane.getChildren().add(button);
			folderImage.add(button);
		}
	}

	private void setDrinkButtons(List<Menu> items, FlowPane pane) {
		Image image = null;
		for (Menu item : items) {
			Button button = new Button(item.getName());
			image = new Image(drinkUrl.get(1));
			ImageView view = new ImageView(image);
			view.setFitHeight(100);
			view.setFitWidth(100);
			button.setPrefSize(100, 100);
			button.setWrapText(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setUserData(item);
			button.setGraphic(view);
			pane.getChildren().add(button);
			folderImage.add(button);
		}
	}

	/**
	 * during the test.
	 */
	public void chooseFile() {
		FileChooser chooser = new FileChooser();
		/** Open file dialog and save it to file */
		File selectedFile = chooser.showOpenDialog(new Stage());
	}

	/**
	 * Method for handling deleteImage button. Delete image in the list view.
	 * 
	 */
	public void deleteImageHandler(ActionEvent event) {
		folderImage.remove(folderImage.size() - 1);
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new Tableview());
	}

	/**
	 * Handler for logout button. When event recieve the Start up scene is shown.
	 * 
	 */
	public void logoutHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Main());
	}
}
