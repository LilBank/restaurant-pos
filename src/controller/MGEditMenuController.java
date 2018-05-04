package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import application.TableView;
import application.Main;
import database.DBManager;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
	/** ListView showing the items */
	@FXML
	ListView<Button> listItems;
	/** Combined with list view */
	@FXML
	ListProperty<Button> listProperty = new SimpleListProperty<>();
	/** List of all images */
	public static List<Button> folderImage = new ArrayList<>();

	/**
	 * Bind listView with ListProperty at the beginning.
	 */
	@FXML
	public void initialize() {
		listProperty.set(FXCollections.observableArrayList(folderImage));
		listItems.itemsProperty().bind(listProperty);
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
		createData("Foods", name, price, url);
	}
	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertDrinkHandler(ActionEvent event) {
		createData("Drinks", name, price, url);
	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void createData(String table, String name, String price, String url) {
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
			alert.setHeaderText("Inputfield Error");
			alert.show();
		} else {
			if (result.isPresent()) {
				DBManager.InsertTo(table, name, price, url);
			}
			image = new Image(result.get());
			Button foods = new Button();
			ImageView view = new ImageView(image);

			/** Set size of the imported image */
			view.setFitHeight(100);
			view.setFitWidth(100);
			foods.setGraphic(view);
			folderImage.add(foods);
			listProperty.set(FXCollections.observableArrayList(folderImage));
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
		listProperty.set(FXCollections.observableArrayList(folderImage));
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new TableView());
	}

	/**
	 * Handler for logout button. When event recieve the Start up scene is shown.
	 * 
	 */
	public void logoutHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) logout.getScene().getWindow(), new Main());
	}
}
