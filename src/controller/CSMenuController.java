package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import application.CSTable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CSMenuController {
	@FXML
	Button back;
	@FXML
	Button newImage;
	/** ListView showing the items */
	@FXML
	ListView<Label> listItems;
	/** Combined with list view */
	@FXML
	ListProperty<Label> listProperty = new SimpleListProperty<>();
	/** List of all images */
	List<Label> folderImage = new ArrayList<>();

	/**
	 * Bind listView with ListProperty at the beginning.
	 */
	@FXML
	public void initialize() {
		listProperty.set(FXCollections.observableArrayList(folderImage));
		listItems.itemsProperty().bind(listProperty);
	}

	/**
	 * Handler for back button. When event receive the CS table scene is shown.
	 * 
	 * @param event
	 */
	public void backButtonHandler(ActionEvent event) {
		ScreenController.switchWindow((Stage) back.getScene().getWindow(), new CSTable());
	}

	/**
	 * Method for handling newImage button. Insert image to the list view.
	 * 
	 * @throws MalformedURLException
	 */
	public void insertImageHandler(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		File selectedFile = chooser.showOpenDialog(new Stage());
		System.out.print(selectedFile.getAbsolutePath());
		Image image = null;
		try {
			image = new Image(selectedFile.toURI().toURL().toExternalForm());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Label food = new Label();
		food.setGraphic(new ImageView(image));
		folderImage.add(food);
		listProperty.set(FXCollections.observableArrayList(folderImage));
	}

}
